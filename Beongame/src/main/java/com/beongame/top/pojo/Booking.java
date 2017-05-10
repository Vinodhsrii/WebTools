package com.beongame.top.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "BookingTable")
public class Booking {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bookingID", unique = true, nullable = false)
	private long id;
    public Booking (){
    	
    }
    
	@OneToOne
	@PrimaryKeyJoinColumn
	private SportsAcademy sportsAcademy;
	
    @ManyToOne
    @JoinColumn(name = "euID")
    private Enduser enduser;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spID")
    private Serviceprovider serviceprovider;
    
    
    public Booking (Enduser eu , SportsAcademy tmp, Serviceprovider spID){
    	this.enduser = eu;
    	this.sportsAcademy = tmp;
    	this.serviceprovider = spID;
    	
    }
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SportsAcademy getSportsAcademy() {
		return sportsAcademy;
	}

	public void setSportsAcademy(SportsAcademy sportsAcademy) {
		this.sportsAcademy = sportsAcademy;
	}

	public Enduser getEnduser() {
		return enduser;
	}

	public void setEnduser(Enduser enduser) {
		this.enduser = enduser;
	}

	public Serviceprovider getServiceProvider() {
		return serviceprovider;
	}

	public void setServiceProvider(Serviceprovider serviceprovider) {
		this.serviceprovider = serviceprovider;
	}
    
    
	
}
