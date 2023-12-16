package com.tap.vaccine.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.RegisterEntity;

@Component
public class RegisterDAOImpl implements RegisterDAO {
	
	private SessionFactory sessionFactory;
	private JavaMailSenderImpl javaMailSenderImpl;
	
	public RegisterDAOImpl() {
		System.out.println("This Default Register DAO Impl");
	}
	
	@Autowired
	public RegisterDAOImpl(SessionFactory sessionFactory,JavaMailSenderImpl javaMailSenderImpl) {
		this.sessionFactory = sessionFactory;
		this.javaMailSenderImpl = javaMailSenderImpl;
	}

	@Override
	public boolean saveRegisterEntity(RegisterEntity entity) {
		Session session = null;
		Transaction transaction = null;
		boolean isValid = false;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Serializable save = session.save(entity);
			System.out.println("Save"+save);
			transaction.commit();
			boolean sendRegistrationEmail = sendRegistrationEmail(entity.getUsername(), entity.getEmail(), entity.getPassword());
			isValid = true;
		}
		catch (Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		
		return isValid;
	}
	
	public boolean sendRegistrationEmail(String username,String email,String password) {
		boolean flag = false;
		try {
			
			System.out.println("Mail APP");
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Registration Successfull");
			message.setText("Dear " + username + ",You Are Successfully Registered To Vaccine." +
					"NOTE: Email: " + email + " Password: " + password +
					" Use these credentials to login");
			javaMailSenderImpl.send(message);
			flag = true;
		}
		catch(MailException e) {
			flag = false;
			e.getMessage();
		}
		
		return flag;
	}

	@Override
	public boolean userLogin(String email, String password) {
		boolean isValid = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String HQL = "select count(id) from RegisterEntity where email=:email and password=:password ";
			long count =(long) session.createQuery(HQL).setParameter("email", email).setParameter("password", password).uniqueResult();
			System.out.println(count);
			
			if(count > 0) {
				isValid = true;
			}
			else {
				isValid = false;
			}
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return isValid;
	}

}
