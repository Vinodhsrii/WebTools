package com.beongame.top.dao;

import org.hibernate.HibernateException;

import com.beongame.top.exception.UserException;
import com.beongame.top.pojo.Booking;
import com.beongame.top.pojo.Enduser;
import com.beongame.top.pojo.Serviceprovider;
import com.beongame.top.pojo.SportsAcademy;

public class BookingDAO extends DAO{
	public BookingDAO() {
		
	}

	public Booking get(){
		return (new Booking());
	}
	
	public Booking addbooking(Enduser eu ,SportsAcademy tmp, Serviceprovider spID) throws UserException{
		try {
			begin();
			Booking boo = new Booking(eu, tmp,spID);
			getSession().save(boo);
			commit();
			return boo;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating Academy: " + e.getMessage());
		}
		
	}
}
