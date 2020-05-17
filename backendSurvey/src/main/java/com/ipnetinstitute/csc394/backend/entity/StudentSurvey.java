package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name= "student_survey")
@AllArgsConstructor@NoArgsConstructor
public class StudentSurvey extends BaseEntity {
	String comments;
	Short is_na;
	Integer rating;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name ="id_survey", nullable= false)
private Survey survey;


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name ="id_student", nullable= false)
private Student student;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name ="id_question", nullable= false)
private Question question;

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

public Survey getSurvey() {
	return survey;
}

public void setSurvey(Survey survey) {
	this.survey = survey;
}

public Student getStudent() {
	return student;
}

public void setStudent(Student student) {
	this.student = student;
}

public Question getQuestion() {
	return question;
}

public void setQuestion(Question question) {
	this.question = question;
}


}
