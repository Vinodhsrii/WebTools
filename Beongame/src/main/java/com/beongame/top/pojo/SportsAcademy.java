package com.beongame.top.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SportsAcademyTable")
public class SportsAcademy implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "spID", unique = true, nullable = false)
	private long spID;
	
	@Column(name = "slotID")
	private String slotID;

	@Column(name = "availability")
	private String availability;

	@Column(name = "price")
	private String price;

	@Column(name = "game")
	private String game;

	@OneToOne(mappedBy = "sportsAcademy", cascade = CascadeType.ALL)
	private Booking booking;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gameID")
	private Sports sports;

	@ManyToOne()
	@JoinColumn(name = "academyID")
	private Academy academy;

	public SportsAcademy() {

	}

	public SportsAcademy(String price, String spslot, String spname, Academy academy) {
		this.price = price;
		this.slotID = spslot;
		this.availability = "A";
		this.academy = academy;
		this.game = spname;

	}

	public long getSpID() {
		return spID;
	}

	public void setSpID(long spID) {
		this.spID = spID;
	}

	public String getSlotID() {
		return slotID;
	}

	public void setSlotID(String slotID) {
		this.slotID = slotID;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Sports getSports() {
		return sports;
	}

	public void setSports(Sports sports) {
		this.sports = sports;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
