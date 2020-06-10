package com.ipnetinstitute.csc394.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;


@Entity
public class Teacher extends BaseEntity{
	
	String matricule;


	public Teacher() {
	}

	public Teacher(String matricule, User user){
		this.matricule = matricule;
		this.user = user;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="id_user",nullable = false, unique = true)
//	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user", nullable = false, unique = true)
	private User user;

	/*
	 * @OneToMany(mappedBy = "teacher", cascade = {CascadeType.ALL}) private
	 * List<Course> courses = new ArrayList<Course>();
	 */
//	@JsonBackReference
//	@JsonManagedReference
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

 
	
}
