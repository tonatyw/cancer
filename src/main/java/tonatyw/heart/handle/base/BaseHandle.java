package tonatyw.heart.handle.base;

import tonatyw.heart.HeartBox;

/**
 * 基础处理类
 * @ClassName BaseHandle
 * @Description: 基础处理类
 * @date 2019年6月10日 下午5:09:11
 */
public interface BaseHandle {
	/**
	 * 统一处理方法
	 * @Title: deal
	 * @Description: 统一处理方法
	 * @param data 数据
	 * @param heartBox 心跳相关信息
	 * @return void
	 */
	public void deal(String data,HeartBox heartBox);
}
