package com.cpc.test;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cpc.oa.domain.User;

@Service("testService")
public class TestService {
	@Resource
	private SessionFactory sessionfactory;

	@Transactional
	public void saveUser(){
		Session session = sessionfactory.getCurrentSession();
		session.save(new User());
		//int i = 1/0;
		session.save(new User());
	}
	
}
