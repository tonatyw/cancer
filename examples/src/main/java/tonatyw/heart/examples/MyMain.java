package tonatyw.heart.examples;

import tonatyw.heart.cancer.Udp;
import tonatyw.heart.examples.constants.Sets;
import tonatyw.heart.examples.thread.MessageSynThread;

public class MyMain {
	public static void main(String[] args) {
		String ip = Sets.confMap.get("ip");
		int port = Integer.parseInt(Sets.confMap.get("port"));
		String pIp = Sets.confMap.get("ip");
		int pPort = Integer.parseInt(Sets.confMap.get("pPort"));
		long timeout = Long.valueOf(Sets.confMap.get("timeout"));
		// 启动心跳线程
    	new Udp(ip, port, pIp, pPort, timeout, Sets.heartBox).begin();
    	// 启动消息同步线程
    	new Thread(new MessageSynThread(Sets.heartBox)).start();
	}
}
