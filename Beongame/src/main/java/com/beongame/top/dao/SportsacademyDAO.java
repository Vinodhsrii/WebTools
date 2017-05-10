package com.beongame.top.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Academy;
import com.beongame.top.pojo.SportsAcademy;

public class SportsacademyDAO extends DAO {

	public SportsacademyDAO() {

	}

	public SportsAcademy get() {
		return (new SportsAcademy());
	}

	public SportsAcademy addslots(String spprice, String spslot, String spname, Academy academy) throws UserException {

		try{
				begin();
				SportsAcademy acad = new SportsAcademy(spprice, spslot,spname,academy);
				getSession().save(acad);
				commit();
				return acad;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Exception while creating Academy: " + e.getMessage());
			}

	}

	public SportsAcademy save(SportsAcademy acad) throws UserException {

		try{
				begin();
				getSession().saveOrUpdate(acad);
				commit();
				return acad;
			} catch (HibernateException e) {
				rollback();
				throw new UserException("Exception while creating Academy: " + e.getMessage());
			}

	}	

	
	public void delete(SportsAcademy acad) throws UserException {
		try {
			begin();
			getSession().delete(acad);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete slots ", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SportsAcademy> list() throws UserException {

		try {
			begin();
			Query q = getSession().createQuery("from SportsAcademy");
			List<SportsAcademy> sportsAcademy = q.list();
			commit();
			return sportsAcademy;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could get the academies" + e.getMessage());
		}

	}
	
}
