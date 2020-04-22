package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_survey_question")
@AllArgsConstructor@NoArgsConstructor
@Data
public class CatSurveyQuestion  extends BaseEntity{
	
	Integer seq_nbr;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_question", nullable = false)
	private Question question;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_cat_survey", nullable = false)
	private CatSurvey cat_survey;

}
