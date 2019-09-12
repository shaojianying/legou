package testdemo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.demo.SpringDemo;
import com.demo.SpringDemo2;

public class SpringTest {
	@Test
	public void test1() {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/SpringTestDemo.xml");
		//从容器中取出bean 容器装入bean时默认类名首字母小写为名字装入
		SpringDemo2 s=(SpringDemo2) context.getBean("springDemo2");
		//s.demo();
		s.demo2();
		//关闭
		//((AbstractApplicationContext) context).close();
		System.out.println(s.getCustomMap().get("1"));
	}
}
