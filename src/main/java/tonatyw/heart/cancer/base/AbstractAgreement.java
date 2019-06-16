package tonatyw.heart.cancer.base;

import tonatyw.heart.cancer.HeartBox;

/**
 * 协议抽象类
 * @ClassName AbstractAgreement
 * @Description: 协议抽象类
 * @date 2019年6月10日 上午10:33:53
 */
public abstract class AbstractAgreement {
	/**
	 * 自动封装参数 还未实现
	 * @param obj
	 * @time 2019年6月10日-上午11:31:30
	 * @todo 自动封装参数 还未实现
	 */
	public AbstractAgreement(String ip,int port,String pIp,int pPort,long timeout,HeartBox heartBoxOut){
		this.heartBox = heartBoxOut;
		heartBox.setIp(ip);
		heartBox.setpIp(pIp);
		heartBox.setPort(port);
		heartBox.setpPort(pPort);
		heartBox.setTimeout(timeout);
	}
	/**
	 * 自动封装参数 还未实现
	 * @param obj
	 * @time 2019年6月10日-上午11:31:30
	 * @todo 自动封装参数 还未实现
	 */
	public AbstractAgreement(String ip,int port,long timeout,HeartBox heartBoxOut){
		this.heartBox = heartBoxOut;
		heartBox.setIp(ip);
		heartBox.setPort(port);
		heartBox.setTimeout(timeout);
	}
	/** 心跳盒子 存储心跳相关信息 */
	protected HeartBox heartBox;
	public abstract void begin();
	public abstract void close();
}
