package com.beongame.top.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="SportsTable")
public class Sports {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gameID", unique = true, nullable = false)
	private long gameID;

	@Column(name = "gameName")
	private String gameName;

	
    @OneToMany(mappedBy = "sports")
    private Set<SportsAcademy> sportsAcademy = new HashSet<SportsAcademy>();
	
	public Sports(){
		
	}
	public Sports(String gameName){
		this.gameName = gameName;
	}
	public long getGameID() {
		return gameID;
	}
	public void setGameID(long gameID) {
		this.gameID = gameID;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Set<SportsAcademy> getSportsAcademy() {
		return sportsAcademy;
	}
	public void setSportsAcademy(Set<SportsAcademy> sportsAcademy) {
		this.sportsAcademy = sportsAcademy;
	}

	
}

