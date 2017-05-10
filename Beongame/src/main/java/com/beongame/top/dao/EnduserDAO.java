package com.beongame.top.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Enduser;
import com.beongame.top.pojo.Person;

public class EnduserDAO extends DAO {
	public EnduserDAO() {

	}

	// Create User/Service Provider
	public Enduser signup(String firstName, String lastName, String age, String emailId, String contactNo,
			String username, String password, String loginType) throws UserException {
		
		firstName = firstName.toUpperCase();
		lastName = lastName.toUpperCase();
		age = age.toUpperCase();
		emailId = emailId.toUpperCase();
		contactNo = contactNo.toUpperCase();
		username = username.toUpperCase();
		password = password.toUpperCase();
		loginType = loginType.toUpperCase();
		try {
			begin();
			Enduser eu = new Enduser();

			eu.setFirstName(firstName);
			eu.setLastName(lastName);
			eu.setAge(age);
			eu.setContactNo(contactNo);
			eu.setEmailId(emailId);
			eu.setLoginType(loginType);
			eu.setPassword(password);
			eu.setUserName(username);
			getSession().save(eu);
			commit();
			return eu;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating End User: " + e.getMessage());
		}
	}

	public Enduser get(String emailId, String password, String loginType) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery(
					"from Person  where emailId = :emailId and password = :password and loginType = :loginType");
			q.setString("emailId", emailId);
			q.setString("password", password);
			q.setString("loginType", loginType);
			Person user = (Person) q.uniqueResult();

			if (user != null) {
				Query q1 = getSession().createQuery("from Enduser where euId    = :euIDV");
				q1.setLong("euIDV", user.getPersonID());
				Enduser eu = (Enduser) q1.uniqueResult();
				commit();
				return eu;
			}

			return null;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + emailId, e);
		}
	}

	// Remove User / Service Provider
	public void delete(Enduser user) throws UserException {
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
