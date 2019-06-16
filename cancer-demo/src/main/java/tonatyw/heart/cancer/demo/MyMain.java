package tonatyw.heart.cancer.demo;

public class MyMain {
	public static void main(String[] args) {
		// 启动心跳线程
		new Udp("127.0.0.1", port, pIp, pPort, timeout, Sets.heartBox).begin();
		
		// 启动消息同步线程
		new Thread(new MessageSynThread(Sets.heartBox)).start();
	}
}
