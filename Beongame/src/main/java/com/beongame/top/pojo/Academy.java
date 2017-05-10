package com.beongame.top.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="AcademyTable")
public class Academy {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="academyID", unique = true, nullable = false)
	private long academyID;

	@Column(name = "academyName")
	private String academyName;

	@Column(name = "academyLocation")
	private String academyLocation;

	@OneToMany(mappedBy = "academy")
    private Set<SportsAcademy> sportsAcademy = new HashSet<SportsAcademy>();

	@OneToOne
	@PrimaryKeyJoinColumn
	private Serviceprovider serviceprovider;
    
	public Academy(){
		
	}
	
    public Academy(String academyName, String academyLocation) {
        this.academyName = academyName;
        this.academyLocation = academyLocation;
    }

	public long getAcademyID() {
		return academyID;
	}

	public void setAcademyID(long academyID) {
		this.academyID = academyID;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getAcademyLocation() {
		return academyLocation;
	}

	public void setAcademyLocation(String academyLocation) {
		this.academyLocation = academyLocation;
	}

	public Set<SportsAcademy> getSportsAcademy() {
		return sportsAcademy;
	}

	public void setSportsAcademy(Set<SportsAcademy> sportsAcademy) {
		this.sportsAcademy = sportsAcademy;
	}

	public Serviceprovider getServiceprovider() {
		return serviceprovider;
	}

	public void setServiceprovider(Serviceprovider serviceprovider) {
		this.serviceprovider = serviceprovider;
	}
	

}

