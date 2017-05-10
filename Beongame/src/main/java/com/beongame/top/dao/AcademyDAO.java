package com.beongame.top.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Academy;

public class AcademyDAO extends DAO {
	public AcademyDAO() {

	}

	// Create Academy
	public Academy addAcademy(String academyName, String academyLocation) throws UserException {
		try {
			begin();
			academyName =  academyName.toUpperCase();
			academyLocation = academyLocation.toUpperCase();
			Academy acad = new Academy(academyName, academyLocation);
			getSession().save(acad);
			commit();
			return acad;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating Academy: " + e.getMessage());
		}
	}

	public Academy checkAcademy(String academyName, String academyLocation) throws UserException {
		try {
			begin();
			academyName =  academyName.toUpperCase();
			academyLocation = academyLocation.toUpperCase();
			Query q = getSession().createQuery("from Academy where academyLocation = :aloc and academyName =:aname");
			q.setString("aloc", academyLocation);
			q.setString("aname", academyName);
			Academy ac = (Academy) q.uniqueResult();
			commit();
			return ac;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating Academy: " + e.getMessage());
		}
	}
	
	public Academy checkAcademy1(String academyName) throws UserException {
		try {
			begin();
			academyName =  academyName.toUpperCase();
			Query q = getSession().createQuery("from Academy where academyName =:aname");
			q.setString("aname", academyName);
			Academy ac = (Academy) q.uniqueResult();
			commit();
			return ac;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating Academy: " + e.getMessage());
		}
	}

	// Create Academy
	public Academy getAcademy(long id) throws UserException {
		try {
			begin();
//			Criteria c = getSession().createCriteria(Academy.class);
//			c.add(Restrictions.eq("academyId", id));
//			Academy ac = (Academy) c.uniqueResult();
			Query q = getSession().createQuery("from Academy where academyId = :Id");
			q.setLong("Id", id);
			Academy ac = (Academy) q.uniqueResult();
			commit();
			return ac;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating Academy: " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Academy> list() throws UserException {

		try {
			begin();
			Query q = getSession().createQuery("from Academy");
			List<Academy> academy = q.list();
			commit();
			return academy;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete advert" + e.getMessage());
		}

	}
}
