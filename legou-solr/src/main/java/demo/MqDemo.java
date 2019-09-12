package demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

//消息中间件测试
public class MqDemo {
	@Test
	public void queuesdemo() throws Exception {//消息生产者
		//点对点模式
		//创建ActiveMQ的连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//通过连接工厂创建连接
		Connection connection =connectionFactory.createConnection();
		//启动连接
		connection.start();
		//通过连接创建session    不支持事务   自动接收
		Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//使用session创建消息队列   并命名
		Queue queue=session.createQueue("NO1");
		//创建消息生产者 producer 并设置消息发送的目的地  让他知道往哪个队列中发
		MessageProducer tiantian=session.createProducer(queue);
		//创建发送消息的内容
		TextMessage messages=session.createTextMessage("锄禾日当午了8");
		//消息生产者发送消息到中间件
		tiantian.send(messages);
		//关闭资源
		tiantian.close();
		session.close();
		connection .close();		
}
	// 消费者 接受消息
	@Test
	public void consumerDame() throws Exception {

		// 创建ActiveMQ的连接工厂
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		// 使用activemq连接工厂创建连接
		Connection connection = connectionFactory.createConnection();
		// 开启connection连接
		connection.start();		
		// 创建session  不创建事务      自动确认
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 通过session创建queue队列 createQueue需要传递queue的名字如果该名字存在则不创建
		Queue queue = session.createQueue("NO1");
		// 创建消费者
		MessageConsumer xiaomei = session.createConsumer(queue);
		// 创建匿名类 new 接口
		xiaomei.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {//监听器
				TextMessage textMessage = (TextMessage) message;
				try {
					String string = textMessage.getText();
					System.out.println(string);	
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		// 阻塞线程
		System.in.read();
		xiaomei.close();
		session.close();
		connection.close();

	}
}
