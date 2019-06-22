package tonatyw.heart.cancer.handle;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tonatyw.heart.cancer.HeartBox;
import tonatyw.heart.cancer.constants.Constants;
import tonatyw.heart.cancer.handle.base.BaseHandle;

/**
 * 接收处理类
 * @ClassName ReceiveHandle
 * @Description: 接收处理类
 * @date 2019年6月10日 下午5:36:34
 */
public class ReceiveHandle implements BaseHandle{
	@Override
	public void deal(String data, HeartBox heartBox) {
		JSONObject json = JSON.parseObject(data);
		// 存储设备数据 key->ip:port value->status 1:在线 0:不在线*/
		heartBox.getTmpDeviceMap().put(json.getString(Constants.HeartMessage.RECEIVE_IP).concat(Constants.HeartMessage.IP_PORT_SPLIT).concat(json.getString(Constants.HeartMessage.RECEIVE_PORT)), 1);
		
		// 获取完整性校验字段
		String sign = json.getString(Constants.HeartMessage.SIGN);
		String msg = json.getString(Constants.HeartMessage.MSG);
		String verifySign = DigestUtils.md5Hex(msg);
		String ip = json.getString(Constants.HeartMessage.RECEIVE_IP);
		int port = json.getIntValue(Constants.HeartMessage.RECEIVE_PORT);
		if(StringUtils.equals(sign, verifySign)){//数据完整
			json.clear();
			// 取出心跳额外数据 存入本地缓存
			try {
				if(StringUtils.isNotEmpty(msg)){
					heartBox.getReceiveQueue().put(msg);
				}
				// 取出socket
				DatagramSocket socket = heartBox.getSocket();
				// 返回字段
				json.put(Constants.HeartMessage.MSGTYPE, 2);
				
				//判断是否需要同步数据
				if(heartBox.getMsgQueueMap().containsKey(ip)){
					String synMessage = heartBox.getMsgQueueMap().get(ip).poll();
					if(StringUtils.isNotEmpty(synMessage)){//如果不为空，需要向设备同步信息
						json.put(Constants.HeartMessage.MSG, synMessage);
					}
				}else{
					heartBox.getMsgQueueMap().put(ip, new ArrayBlockingQueue<String>(1000, true));
				}
				json.put(Constants.HeartMessage.SIGN, DigestUtils.md5Hex(json.getString(Constants.HeartMessage.MSG)==null?"":json.getString(Constants.HeartMessage.MSG)));
				json.put("removeSign", sign);
				byte[] bytes = json.toJSONString().getBytes(Constants.HeartMessage.ENCODING_UTF_8);
				DatagramPacket dp = new DatagramPacket(bytes, bytes.length, new InetSocketAddress(ip, port));
				socket.send(dp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
