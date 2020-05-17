package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor@NoArgsConstructor
public class Survey extends BaseEntity{

	
	String title, description, begin_message, end_message;
	
	Date begin_date, end_date;
	
	Integer status;
	

	
	@OneToMany(mappedBy = "survey" , cascade = {CascadeType.ALL})
	private List<StudentSurvey> student_surveys = new ArrayList<StudentSurvey>();


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_cat_survey", nullable = false)
	private CatSurvey cat_survey;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_course", nullable = false)
	private Course course;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBegin_message() {
		return begin_message;
	}

	public void setBegin_message(String begin_message) {
		this.begin_message = begin_message;
	}

	public String getEnd_message() {
		return end_message;
	}

	public void setEnd_message(String end_message) {
		this.end_message = end_message;
	}

	public Date getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(Date begin_date) {
		this.begin_date = begin_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<StudentSurvey> getStudent_surveys() {
		return student_surveys;
	}

	public void setStudent_surveys(List<StudentSurvey> student_surveys) {
		this.student_surveys = student_surveys;
	}

	public CatSurvey getCat_survey() {
		return cat_survey;
	}

	public void setCat_survey(CatSurvey cat_survey) {
		this.cat_survey = cat_survey;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	// @OneToMany(mappedBy = "student", cascade = { CascadeType.ALL })
	// private List<StudentSurvey> student_surveys = new ArrayList<StudentSurvey>();

}
