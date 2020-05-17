package com.ipnetinstitute.csc394.backend.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor@NoArgsConstructor
@Data
public class Question  extends BaseEntity{
	
	
	String title;
	@OneToMany(mappedBy = "question", cascade = { CascadeType.ALL })
	// @OneToMany(cascade = { CascadeType.ALL })
	private List<CatSurveyQuestion> cat_survey_questions = new ArrayList<>();
	
	@OneToMany(mappedBy = "question", cascade = {CascadeType.ALL})
	private List<StudentSurvey> studentSurveys = new ArrayList<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CatSurveyQuestion> getCat_survey_questions() {
		return cat_survey_questions;
	}

	public void setCat_survey_questions(List<CatSurveyQuestion> cat_survey_questions) {
		this.cat_survey_questions = cat_survey_questions;
	}

	public List<StudentSurvey> getStudentSurveys() {
		return studentSurveys;
	}

	public void setStudentSurveys(List<StudentSurvey> studentSurveys) {
		this.studentSurveys = studentSurveys;
	};
	

}
