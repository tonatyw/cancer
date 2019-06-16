package tonatyw.heart.thread;

import tonatyw.heart.HeartBox;

/**
 * 修改设备状态
 * @ClassName StatusThread
 * @Description: 修改设备状态
 * @date 2019年6月11日 下午2:15:51
 */
public class StatusThread implements Runnable{
	/** 心跳信息 */
	private HeartBox heartBox;
	public StatusThread(HeartBox heartBox){
		this.heartBox = heartBox;
	}
	@Override
	public void run() {
		// 更新状态
		heartBox.getDeivceMap().putAll(heartBox.getTmpDeviceMap());
		heartBox.getTmpDeviceMap().forEach((key,value)->{
			value = 0;
		});
	}

}
