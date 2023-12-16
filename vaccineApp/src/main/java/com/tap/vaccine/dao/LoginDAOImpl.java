package com.tap.vaccine.dao;

import org.hibernate.HibernateException;
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
import com.tap.vaccine.exception.EntityNotFoundException;

@Component
public class LoginDAOImpl implements LoginDAO {
	
	public LoginDAOImpl() {
		System.out.println("Default LoginDAO IMPL");
	}
	private SessionFactory sessionFactory;
	private JavaMailSenderImpl javaMailSenderImpl;
	
	@Autowired
	public LoginDAOImpl(SessionFactory sessionFactory,JavaMailSenderImpl javaMailSenderImpl) {
		this.sessionFactory = sessionFactory;
		this.javaMailSenderImpl = javaMailSenderImpl;
	}
	
	@Override
	public RegisterEntity getRegisterEntityByEmail(String email) throws EntityNotFoundException {
		Session session = null;
		RegisterEntity entity = null;
		String hql = "from RegisterEntity re where re.email='"+email+"'";
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(hql);
			entity = (RegisterEntity) query.uniqueResult();
			System.out.println(entity);
			
			if(entity != null) {
				return entity;
			}
			else {
				throw new EntityNotFoundException("RegisterEntity with email "+email+" not found");
			}
		}
		catch(HibernateException e) {
			System.out.println("Exception in getting RegisterEntityByEmail "+e.getMessage());
			throw new EntityNotFoundException("Exception in getting RegisterEntityByEmail "+e.getMessage());
		}
		finally {
			if(session != null) {
				session.close();
				System.out.println("Session Closed");
			}
		}
	}

	@Override
	public boolean updateRegisterEntity(String email,int newLoginAttempts) {
		System.out.println("Update entity Invoked");
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			String UPDATE_QUERY = "update RegisterEntity re set re.loginAttempts=:newLoginAttempts where re.email=:email";
			Query query = session.createQuery(UPDATE_QUERY);
			query.setParameter("newLoginAttempts", newLoginAttempts);
			query.setParameter("email", email);
			int i = query.executeUpdate();
			transaction.commit();
			if(i > 0) {
				return true;
			}else {
				return false;
			}
		}
		finally {
			if(session != null) {
				session.close();
				System.out.println("Session closed");
			}
		}
	}

	@Override
	public boolean sendAccountBlockingEmail(String email,String username) {
		boolean flag = false;
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Account Blocked");
			message.setText("your account is blocked due to multiple incorrect login credentials please reset the password and login again");
			javaMailSenderImpl.send(message);
			flag = true;
		}
		catch (MailException e) {
			flag = false;
			e.getMessage();
		}
		return flag;
	}

}
