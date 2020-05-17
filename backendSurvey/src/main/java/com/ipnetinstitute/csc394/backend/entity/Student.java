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

@Entity
@Table(name="student")
public class Student extends BaseEntity{
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
	

	@OneToMany(mappedBy = "student", cascade = {CascadeType.ALL})
	private List<StudentCourse> studentCourses = new ArrayList<StudentCourse>();
	

	
	@OneToMany(mappedBy = "student", cascade = {CascadeType.ALL})
	private List<StudentSurvey> student_surveys = new ArrayList<StudentSurvey>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	public List<StudentSurvey> getStudent_surveys() {
		return student_surveys;
	}

	public void setStudent_surveys(List<StudentSurvey> student_surveys) {
		this.student_surveys = student_surveys;
	}
	
	
	
	
}
