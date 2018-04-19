package com.sl.www.test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sl.www.domain.User;

@Service
public class TestService {
	
	@Resource
	private SessionFactory sf;
	
	@Transactional
	public void saveTwoUser(){
		Session session=sf.getCurrentSession();
		session.save(new User());
		//int i=1/0;
		session.save(new User());
	}

}
