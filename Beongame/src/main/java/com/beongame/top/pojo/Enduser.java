package com.beongame.top.pojo;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "EndUser")
@PrimaryKeyJoinColumn(name = "euID")
public class Enduser extends Person {

	public Enduser() {

	}
	
	@OneToMany(mappedBy = "enduser")
	private Set<Booking> userGroups = new HashSet<Booking>();

	public Set<Booking> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<Booking> userGroups) {
		this.userGroups = userGroups;
	}
	
	
}