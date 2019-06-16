package tonatyw.heart.cancer;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import tonatyw.heart.cancer.base.AbstractAgreement;
import tonatyw.heart.cancer.thread.Receive;
import tonatyw.heart.cancer.thread.Send;
import tonatyw.heart.cancer.thread.StatusThread;

public class Udp extends AbstractAgreement{
	private DatagramSocket socket;
	/**
	 * 初始化构造函数
	 * @param ip 本机ip
	 * @param port 本机端口
	 * @param pIp 父级ip
	 * @param pPort 父级端口
	 * @param timeout
	 * @time 2019年6月10日-上午11:54:59
	 */
	public Udp(String ip,int port,String pIp,int pPort,long timeout,HeartBox heartBoxOut){
		// 调用父类初始化参数
		super(ip,port,pIp,pPort,timeout,heartBoxOut);
		// 初始化参数
		try {
			socket = new DatagramSocket(port);
			// 将连接存入heartBox
			heartBox.setSocket(socket);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void begin() {
		// 等待xms 执行 状态变更任务
		// 声明一个定时任务
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		//如果父级ip有值 需要上报心跳
		if(StringUtils.isNotEmpty(heartBox.getpIp())){
			// 任务执行完后 等待xms再次执行
			pool.scheduleWithFixedDelay(new Send(socket, heartBox), heartBox.getTimeout(), heartBox.getTimeout(), TimeUnit.MILLISECONDS);
		}
		
		pool.scheduleWithFixedDelay(new StatusThread(heartBox), heartBox.getTimeout(), heartBox.getTimeout(), TimeUnit.MILLISECONDS);
		
		// 启动接收
		new Thread(new Receive(socket, heartBox)).start();
		
	}

	@Override
	public void close() {
	}

}
