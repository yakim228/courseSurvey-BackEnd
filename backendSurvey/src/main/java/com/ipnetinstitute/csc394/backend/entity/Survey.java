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

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
	
	

	// @OneToMany(mappedBy = "student", cascade = { CascadeType.ALL })
	// private List<StudentSurvey> student_surveys = new ArrayList<StudentSurvey>();

}
