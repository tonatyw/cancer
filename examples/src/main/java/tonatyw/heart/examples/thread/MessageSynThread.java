package tonatyw.heart.examples.thread;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import tonatyw.heart.cancer.HeartBox;
import tonatyw.heart.cancer.util.ReflectUtil;
import tonatyw.heart.examples.constants.Sets;
import tonatyw.heart.examples.handle.MessageSynHandle;

/**
 * 消息同步线程
 * @ClassName MessageSynThread
 * @Description: 消息同步线程
 * @author Tangjc
 * @date 2019年6月12日 下午5:19:07
 */
public class MessageSynThread implements Runnable{
	/** 心跳相关信息 */
	private HeartBox heartBox;
	public MessageSynThread(HeartBox heartBox) {
		this.heartBox = heartBox;
	}
	@Override
	public void run() {
		// 轮询取同步消息
		while(true){
			try {
				/** 
				 	同步消息 分为三个字段 
					同步数据:data;
					同步数据类型:type -> task,resource;
				 	同步方式: method -> add update del
				*/
				String data = heartBox.getReceiveQueue().take();
				JSONObject json = JSON.parseObject(data);
				if(!Sets.synMap.containsKey(json.getString("type"))){
					Sets.synMap.put(json.getString("type"), new HashMap<String,Object>());
				}
				Map<String,Object> typeMap = Sets.synMap.get(json.getString("type"));
				try {
					Map<String,Object> dataMap = JSON.parseObject(json.getString("data"), new HashMap<String,Object>().getClass());
					ReflectUtil.processMethod(MessageSynHandle.class, json.getString("method"), typeMap,dataMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
