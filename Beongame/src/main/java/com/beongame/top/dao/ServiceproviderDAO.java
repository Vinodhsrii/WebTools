package com.beongame.top.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Academy;
import com.beongame.top.pojo.Person;
import com.beongame.top.pojo.Serviceprovider;

public class ServiceproviderDAO extends DAO {
	public ServiceproviderDAO() {

	}

	// Create User/Service Provider
	public Serviceprovider signup(String firstName, String lastName, String age, String emailId, String contactNo,
			 String password, String loginType, Academy acad) throws UserException {
		firstName = firstName.toUpperCase();
		lastName = lastName.toUpperCase();
		emailId = emailId.toUpperCase();
//		username = username.toUpperCase();
		loginType = loginType.toUpperCase();
		try {
			begin();
			Serviceprovider sp = new Serviceprovider();
			sp.setAcademy(acad);
			sp.setAcadId(acad.getAcademyID());
			sp.setFirstName(firstName);
			sp.setLastName(lastName);
			sp.setAge(age);
			sp.setContactNo(contactNo);
			sp.setEmailId(emailId);
			sp.setLoginType(loginType);
			sp.setPassword(password);
//			sp.setUserName(username);
			getSession().save(sp);
			commit();
			return sp;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating ServiceProvider: " + e.getMessage());
		}
	}

	// Check email 
	// Create User/Service Provider
		public Person checkEmail(String emailId) throws UserException {
			try {
				begin();
				emailId = emailId.toUpperCase();
				Query q = getSession()
						.createQuery("from Person where emailId = :emailId");
				q.setString("emailId", emailId);
				Person user = (Person) q.uniqueResult();
				commit();
				return user;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Exception while creating ServiceProvider: " + e.getMessage());
			}
		}
	
	
	
	
	
	// Create User/Service Provider
	public Person scheck(String emailId) throws UserException {
		try {
			begin();
			emailId = emailId.toUpperCase();
			Query q = getSession()
					.createQuery("from Person where emailId = :emailId");
			q.setString("emailId", emailId);
			Person user = (Person) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating ServiceProvider: " + e.getMessage());
		}
	}

	public Serviceprovider get(String emailId, String password, String loginType) throws UserException {
		try {
			begin();

			Query q = getSession().createQuery(
					"from Person where emailId = :emailId and password = :password and loginType = :loginType");
			q.setString("emailId", emailId);
			q.setString("password", password);
			q.setString("loginType", loginType);
			Person user = (Person) q.uniqueResult();
			if (user != null) {
				Query q1 = getSession().createQuery("from Serviceprovider where spID    = :spID");
				q1.setLong("spID", user.getPersonID());
				Serviceprovider su = (Serviceprovider) q1.uniqueResult();
				commit();
				return su;
			} else {
				return null;
			}
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + emailId, e);
		}
	}

	public Serviceprovider getObjsp(long person) throws UserException {
		try {
			begin();

			Query q1 = getSession().createQuery("from Serviceprovider where spID = :spID");
			q1.setLong("spID", person);
			Serviceprovider su = (Serviceprovider) q1.uniqueResult();
			commit();
			return su;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ");
		}
	}
	
	public com.beongame.top.pojo.Serviceprovider getObj(Academy acd) throws UserException {
		try {
			begin();

			Query q1 = getSession().createQuery("from Serviceprovider where academyID = :acdID");
			q1.setLong("acdID", acd.getAcademyID());
			Serviceprovider su = (Serviceprovider) q1.uniqueResult();
			commit();
			return su;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ");
		}
	}

	
	// Remove User / Service Provider
	public void delete(Serviceprovider user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUserName(), e);
		}
	}

}
