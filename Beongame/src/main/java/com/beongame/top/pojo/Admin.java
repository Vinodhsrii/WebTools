
package com.beongame.top.pojo;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
@PrimaryKeyJoinColumn(name = "adID")
public class Admin extends Person {

	public Admin() {

	}

}