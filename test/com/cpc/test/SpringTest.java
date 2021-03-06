package com.cpc.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class SpringTest {
	private ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
	
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	@Test
	public void testTransaction(){
		TestService testService = (TestService) ac.getBean("testService");
		testService.saveUser2();
	}
	
}
