package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cat_survey_question")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Getter
//@Setter
public class CatSurveyQuestion  extends BaseEntity{
	
	Integer seq_nbr;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_question", nullable = false , updatable = false, insertable = false)
	private Question question;

	Integer id_question;

	public Integer getId_question() {
		return id_question;
	}

	public void setId_question(Integer id_question) {
		this.id_question = id_question;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_cat_survey", nullable = false , updatable = false, insertable = false)
	private CatSurvey cat_survey;

	Integer id_cat_survey;

	public Integer getId_cat_survey() {
		return id_cat_survey;
	}

	public void setId_cat_survey(Integer id_cat_survey) {
		this.id_cat_survey = id_cat_survey;
	}
}
