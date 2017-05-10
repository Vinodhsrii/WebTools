package com.beongame.top.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Person;


public class UserDAO extends DAO {

	public UserDAO() {
		
	}

	// check login credentials with three fields (email, password, loginType)
	public Person get(String emailId, String password,String loginType) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Person where emailId = :emailId and password = :password and loginType = :loginType");
			q.setString("emailId", emailId);
			q.setString("password", password);
			q.setString("loginType", loginType);
			Person user = (Person) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + emailId, e);
		}
	}

	public Person getnew(String emailId) throws UserException {
		try {
			begin();
			Query q  = getSession().createQuery("from Person  where emailId = :emailId");
			q.setString("emailId", emailId);
			Person user = (Person) q.uniqueResult();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + emailId, e);
		}
	}
	
}