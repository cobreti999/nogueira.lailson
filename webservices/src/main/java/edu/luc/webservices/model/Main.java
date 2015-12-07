package edu.luc.webservices.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
		public static void main(String[] args)
		{
			System.out.println("oi");
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); 

			Session session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Customer testCustomer = new Customer("Lailson","6230 N Kenmore Ave", 60660, "Chicago", "USA", 1111111);
			
			CustomerPayment testPayment = new CustomerPayment(testCustomer, "4Debit Card - 1234 5678 1919 2020", "6230 N Kenmore Ave");
			
			session.save(testCustomer);
			session.save(testPayment);
			
			session.getTransaction().commit();
			//session.close();
			System.out.println("test");
		}
}
