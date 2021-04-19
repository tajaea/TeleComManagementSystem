package com.microstar.cablevision.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import microStarCableVision.Employee;


public class EmployeeSessionFactoryBuilder {
	private static SessionFactory session = null;
	
	public static SessionFactory getSessionFactory () {
		if (session==null) {
			session=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
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
