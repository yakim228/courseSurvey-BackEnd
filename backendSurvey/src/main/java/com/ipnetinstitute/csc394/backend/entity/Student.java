package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="student")
//@Getter 
//@Setter
public class Student extends BaseEntity{
	String matricule;

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user", nullable = false, referencedColumnName = "id", updatable = false, insertable = false)
	private User user;

	Integer id_user;

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}


	/*
	 * @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL}) private
	 * List<StudentCourse> studentCourses = new ArrayList<StudentCourse>();
	 */
	

	
	/*
	 * @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL}) private
	 * List<StudentSurvey> student_surveys = new ArrayList<StudentSurvey>();
	 */

}
