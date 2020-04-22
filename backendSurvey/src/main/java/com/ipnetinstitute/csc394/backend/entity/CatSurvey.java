package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "cat_survey")
@AllArgsConstructor@NoArgsConstructor
@Data
public class CatSurvey extends BaseEntity{
	
	String name;
	String description;
	
	@OneToMany(mappedBy = "cat_survey", cascade = {CascadeType.ALL})
	private  List<CatSurveyQuestion> catSurveyQuestions = new ArrayList<>();

	@OneToMany(mappedBy = "cat_survey", cascade = {CascadeType.ALL})
	private List<Survey> surveys = new ArrayList<Survey>();

	
	
}
