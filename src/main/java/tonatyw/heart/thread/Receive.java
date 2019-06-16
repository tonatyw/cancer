package tonatyw.heart.thread;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tonatyw.heart.HeartBox;
import tonatyw.heart.constants.Constants;
import tonatyw.heart.util.ByteUtil;
import tonatyw.heart.util.ReflectUtil;

/**
 * 接收线程
 * @ClassName Receive
 * @Description: 接收线程
 * @date 2019年6月10日 下午3:58:34
 */
public class Receive implements Runnable{
	/** 连接对象 */
	private DatagramSocket socket;
	/** 心跳相关信息 */
	private HeartBox heartBox;
	/** spring取properties对象 */
	/**
	 * 构造方法
	 * @param socket
	 * @time 2019年6月10日-下午6:08:24
	 * @todo 构造方法
	 */
	public Receive(DatagramSocket socket,HeartBox heartBox){
		this.heartBox = heartBox;
		this.socket = socket;
	}
	@Override
	public void run() {
		byte[] bytes = new byte[1024];
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
		while(true){
			try {
				socket.receive(dp);
				int len = dp.getLength();
				byte[] resBytes = ByteUtil.getCopyByte(dp.getData(), len);
				String str = new String(resBytes,Constants.HeartMessage.ENCODING_UTF_8);
				// 转化为json
				JSONObject json = JSON.parseObject(str);
				// 获取消息类型 1 发送 2 回执
				String msgType = json.getString(Constants.HeartMessage.MSGTYPE);
				String ip = dp.getAddress().toString();
				json.put(Constants.HeartMessage.RECEIVE_IP, ip.substring(1, ip.length()));
				json.put(Constants.HeartMessage.RECEIVE_PORT, dp.getPort());
				String classPath = HeartBox.getHeartMap().get(msgType);
				ReflectUtil.processMethod(Class.forName(classPath), "deal", json.toJSONString(),heartBox);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	public static void main(String[] args) {
		while(System.currentTimeMillis()%1000==0){
			System.out.println(System.currentTimeMillis());
		}
	}
}
