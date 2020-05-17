package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "cat_survey")
@AllArgsConstructor@NoArgsConstructor
@Getter @Setter
public class CatSurvey extends BaseEntity{
	
	String name;
	String description;
	
	@OneToMany(mappedBy = "cat_survey", cascade = {CascadeType.ALL})
	private  List<CatSurveyQuestion> catSurveyQuestions = new ArrayList<>();

	@OneToMany(mappedBy = "cat_survey", cascade = {CascadeType.ALL})
	private List<Survey> surveys = new ArrayList<Survey>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CatSurveyQuestion> getCatSurveyQuestions() {
		return catSurveyQuestions;
	}

	public void setCatSurveyQuestions(List<CatSurveyQuestion> catSurveyQuestions) {
		this.catSurveyQuestions = catSurveyQuestions;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	
	
}
