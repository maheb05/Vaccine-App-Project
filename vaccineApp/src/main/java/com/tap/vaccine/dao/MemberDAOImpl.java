package com.tap.vaccine.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.MemberEntity;
import com.tap.vaccine.entity.RegisterEntity;

@Component
public class MemberDAOImpl implements MemberDAO {
	
	private SessionFactory sessionFactory;
	
	public MemberDAOImpl(){
		System.out.println("Default "+this.getClass().getSimpleName());
	}
	
	@Autowired
	public MemberDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public boolean saveMemberEntity(MemberEntity entity) {
		boolean isValid = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			Serializable save = session.save(entity);
			System.out.println(save);
			transaction.commit();
			isValid = true;
		}
		catch (Exception e) {
			e.getMessage();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return isValid;
	}

	@Override
	public List<MemberEntity> viewAllMembers(String userEmail) {
		Session session = null;
		List<MemberEntity> result = null;
		try {
			session = sessionFactory.openSession();
			String GETALL_QUERY ="from MemberEntity me where me.userEmail='"+userEmail+"'";
			Query query = session.createQuery(GETALL_QUERY);
			result = query.getResultList();
		}
		catch (Exception e) {
			e.getMessage();
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public int updateMemberCount(String email, int memberCount) {
		Session session = null;
		Transaction transaction  = null;
		int increaseCount = ++memberCount;
		String UPDATE_COUNT = "update RegisterEntity re set re.memberCount="+increaseCount+" where re.email='"+email+"'";
		int count = 0;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery(UPDATE_COUNT);
			count = query.executeUpdate();
			transaction.commit();
		}
		catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			System.out.println("transaction rolled back");
		}
		finally {
			if(session != null) {
				session.close();
				System.out.println("session is closed");
			}
		}
		return count;
	}

	@Override
	public int getMemberCount(String email) {
		Session session  = null;
		String getCount = "from RegisterEntity re where re.email='"+email+"'";
		RegisterEntity entity = null;
		int count = 0;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery(getCount);
			entity = (RegisterEntity) query.uniqueResult();
			count = entity.getMemberCount();
			System.out.println(count);
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return count;
	}

	@Override
	public MemberEntity getEntityById(int memberId) {
		Session session = null;
		MemberEntity entity = null;
		try {
			session = sessionFactory.openSession();
			entity = session.get(MemberEntity.class, memberId);
			if(entity != null) {
				return entity;
			}else {
				System.out.println("entity is null");
			}
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return entity;
	}

	@Override
	public boolean updateMemberEntity(MemberEntity entity) {
		boolean flag = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(entity);
			transaction.commit();
			flag = true;
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return flag;
	}

	@Override
	public boolean deleteMemberEntityById(int id) {
		
		boolean flag = false;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			MemberEntity entity = session.get(MemberEntity.class, id);
			session.delete(entity);
			transaction.commit();
			flag = true;
		}
		finally {
			if(session != null) {
				session.close();
			}
		}
		return flag;
	}
	
	@Override
	public int decreaseMemberCount(String email, int memberCount) {
		Session session = null;
		Query query = null;
		Transaction transaction = null;
		int increaseCount = --memberCount;
		int count=0;
		String hql= "UPDATE RegisterEntity set memberCount="+increaseCount+" WHERE email='"+email+"'";
		try {
			session = sessionFactory.openSession();
			query = session.createQuery(hql);
			transaction = session.beginTransaction();
			count = query.executeUpdate();
			transaction.commit();
		}catch(HibernateException hibernateException) {
			transaction.rollback();
			System.out.println("Transaction has been rolled back "+ hibernateException.getMessage());
		}finally {
			if(session!=null) {
				session.close();
				System.out.println("session is closed");
			}
		}
		return count;
	}
}
