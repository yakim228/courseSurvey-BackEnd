package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
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
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Getter 
//@Setter
public class Course extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_term", referencedColumnName ="id" ,nullable = false, insertable = false, updatable = false)
	private Term term;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_class", referencedColumnName ="id" ,nullable = false, insertable = false, updatable = false)
	private Classe classe;


	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_subject", referencedColumnName ="id" ,nullable = false, insertable = false, updatable = false)
	private Subject subject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_teacher", referencedColumnName ="id" ,nullable = false, insertable = false, updatable = false)
	private Teacher teacher;

	Integer id_teacher, id_subject, id_class, id_term;

	public Integer getId_term() {
		return id_term;
	}

	public void setId_term(Integer id_term) {
		this.id_term = id_term;
	}

	public Integer getId_teacher() {
		return id_teacher;
	}

	public void setId_teacher(Integer id_teacher) {
		this.id_teacher = id_teacher;
	}

	public Integer getId_subject() {
		return id_subject;
	}

	public void setId_subject(Integer id_subject) {
		this.id_subject = id_subject;
	}

	public Integer getId_class() {
		return id_class;
	}

	public void setId_class(Integer id_class) {
		this.id_class = id_class;
	}

//	public Term getTerm() {
//		return term;
//	}

//	public void setTerm(Term term) {
//		this.term = term;
//	}

//	public Classe getClasse() {
//		return classe;
//	}

//	public void setClasse(Classe classe) {
//		this.classe = classe;
//	}

//	public Subject getSubject() {
//		return subject;
//	}

//	public void setSubject(Subject subject) {
//		this.subject = subject;
//	}

//	public Teacher getTeacher() {
//		return teacher;
//	}

//	public void setTeacher(Teacher teacher) {
//		this.teacher = teacher;
//	}
	/*
	 * @OneToMany(mappedBy = "course", cascade = { CascadeType.ALL }) private
	 * List<Survey> surveys = new ArrayList<Survey>();
	 */
}
