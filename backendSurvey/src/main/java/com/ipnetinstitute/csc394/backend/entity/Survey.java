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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class Survey extends BaseEntity {

	String title, description, beginMessage, endMessage;

	Date beginDate, endDate;

	Integer status;
	
	
	/*
	 * @OneToMany(mappedBy = "survey", cascade = { CascadeType.ALL }) private
	 * List<StudentSurvey> student_surveys = new ArrayList<StudentSurvey>();
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeginMessage() {
		return beginMessage;
	}

	public void setBeginMessage(String beginMessage) {
		this.beginMessage = beginMessage;
	}

	public String getEndMessage() {
		return endMessage;
	}

	public void setEndMessage(String endMessage) {
		this.endMessage = endMessage;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cat_survey", nullable = false)
	private CatSurvey cat_survey;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_course", nullable = false)
	private Course course;

}
