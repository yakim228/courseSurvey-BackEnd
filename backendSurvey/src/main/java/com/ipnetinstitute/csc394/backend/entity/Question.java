package com.ipnetinstitute.csc394.backend.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter 
@Setter
public class Question  extends BaseEntity{	
	private String title;
	/*
	 * @OneToMany(mappedBy = "question", cascade = { CascadeType.ALL })
	 * // @OneToMany(cascade = { CascadeType.ALL }) private List<CatSurveyQuestion>
	 * cat_survey_questions = new ArrayList<>();
	 * 
	 * @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL}) private
	 * List<StudentSurvey> studentSurveys = new ArrayList<>();
	 */
        
        
}
