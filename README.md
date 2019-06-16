# cancer
这是一个实现心跳机制的数据同步框架

# 使用
```java
// 本节点ip
String ip = "127.0.0.1";
// 本节点端口
int port = 8108;
// 父级ip
String pIp = "192.168.1.100";
// 父级端口
int pPort = 8109;
// 初始化一个心跳盒子，用于装载心跳间的
HeartBox heartBox = new HeartBox();
// 启动心跳线程
new Udp(ip, port, pIp, pPort, timeout, heartBox).begin();
```
详细介绍请看这里[Java Cancer 实现心跳机制的数据同步框架](https://blog.csdn.net/forintiii/article/details/92446376)
