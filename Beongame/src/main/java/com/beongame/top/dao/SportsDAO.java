package com.beongame.top.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Sports;

public class SportsDAO extends DAO {

	public SportsDAO() {

	}

	// Create Sports

	public Sports checkSports(String sportsName) throws UserException {
		try {
			begin();
			sportsName = sportsName.toUpperCase();;

			// Check if the same value available in DB already
			Query q = getSession().createQuery("from Sports where gameName = :gName");
			q.setString("gName", sportsName);
			Sports sp = (Sports) q.uniqueResult();
			commit();
			return sp;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating Sports: " + e.getMessage());
		}
	}

	public Sports addSports(String sportsName) throws UserException {
		try {
			begin();
			sportsName = sportsName.toUpperCase();
			Sports game = new Sports(sportsName);
			getSession().save(game);
			commit();
			return game;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating Sports: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Sports> list() throws UserException {

		try {
			begin();
			Query q = getSession().createQuery("from Sports");
			List<Sports> sports = q.list();
			commit();
			return sports;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete advert" + e.getMessage());
		}

	}
}