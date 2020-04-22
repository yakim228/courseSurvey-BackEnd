package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor@NoArgsConstructor
@Data
public class Course extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_term", nullable = false)
	private Term term;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_class", nullable = false)
	private Classe classe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_subject", nullable = false)
	private Subject subject;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_teacher", nullable = false)
	private Teacher teacher;
}
