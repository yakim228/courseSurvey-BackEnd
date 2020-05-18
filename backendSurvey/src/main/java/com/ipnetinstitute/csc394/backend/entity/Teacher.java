package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Teacher extends BaseEntity{
	
	String matricule;

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user", nullable = false, unique = true)
	private User user;
	
	/*
	 * @OneToMany(mappedBy = "teacher", cascade = {CascadeType.ALL}) private
	 * List<Course> courses = new ArrayList<Course>();
	 */

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

 
	
}
