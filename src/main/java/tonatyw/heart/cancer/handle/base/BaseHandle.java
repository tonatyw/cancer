package tonatyw.heart.cancer.handle.base;

import tonatyw.heart.cancer.HeartBox;

/**
 * 基础处理类
 * @ClassName BaseHandle
 * @Description: 基础处理类
 * @author Tangjc
 * @date 2019年6月10日 下午5:09:11
 */
public interface BaseHandle {
	/**
	 * 统一处理方法
	 * @Title: deal
	 * @Description: 统一处理方法
	 * @author Tangjc
	 * @param data 数据
	 * @param heartBox 心跳相关信息
	 * @return void
	 */
	public void deal(String data,HeartBox heartBox);
}
