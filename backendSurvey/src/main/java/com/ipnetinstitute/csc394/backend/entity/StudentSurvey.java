package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@Entity
@Table(name = "student_survey")
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class StudentSurvey extends BaseEntity {
	String comments;
	Short is_na;
	Integer rating;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_survey", nullable = false,referencedColumnName = "id", updatable = false, insertable = false)
	private Survey survey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_student", nullable = false, referencedColumnName = "id", updatable = false, insertable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_question", nullable = false, referencedColumnName = "id", updatable = false, insertable = false)
	private Question question;

	Integer id_survey, id_student, id_question;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Short getIs_na() {
		return is_na;
	}

	public void setIs_na(Short is_na) {
		this.is_na = is_na;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

//	public Survey getSurvey() {
//		return survey;
//	}
//
//	public void setSurvey(Survey survey) {
//		this.survey = survey;
//	}

	public Integer getId_survey() {
		return id_survey;
	}

	public void setId_survey(Integer id_survey) {
		this.id_survey = id_survey;
	}

	public Integer getId_student() {
		return id_student;
	}

	public void setId_student(Integer id_student) {
		this.id_student = id_student;
	}

	public Integer getId_question() {
		return id_question;
	}

	public void setId_question(Integer id_question) {
		this.id_question = id_question;
	}
}
