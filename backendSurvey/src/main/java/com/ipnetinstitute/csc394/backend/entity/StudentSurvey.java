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


}
