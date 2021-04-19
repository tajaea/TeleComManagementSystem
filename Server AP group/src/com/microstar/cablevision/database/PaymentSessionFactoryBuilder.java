package com.microstar.cablevision.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import microStarCableVision.Customer;
import microStarCableVision.PaymentHistory;


public class PaymentSessionFactoryBuilder {
	private static SessionFactory session = null;
	
	public static SessionFactory getSessionFactory () {
		if (session==null) {
			session=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PaymentHistory.class).buildSessionFactory();
			//System.out.println("I'm here");
		}
		return session;
	}
	public static void closeSessionFactory () {
		if(session!=null) {
			session.close();
		}
		
	}

}
