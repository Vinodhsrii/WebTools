
package com.beongame.top.pojo;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "ServiceProvider")
@PrimaryKeyJoinColumn(name = "spID")
public class Serviceprovider extends Person {
    	
	public Serviceprovider() {
	}
	
	@OneToMany(mappedBy = "serviceprovider")
	private Set<Booking> userGroups = new HashSet<Booking>();

	@OneToOne(mappedBy = "serviceprovider", cascade = CascadeType.ALL)
	private Academy academy;
	
	@Column(name = "academyID")
	private long acadId;
	
	public Set<Booking> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<Booking> userGroups) {
		this.userGroups = userGroups;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public long getAcadId() {
		return acadId;
	}

	public void setAcadId(long acadId) {
		this.acadId = acadId;
	}
	
	
	
}