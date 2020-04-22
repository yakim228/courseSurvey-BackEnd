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
@Table(name= "student_course")
@AllArgsConstructor@NoArgsConstructor
@Data
public class StudentCourse extends BaseEntity{
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_course", nullable = false)
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_student", nullable = false)
	private Student student;

}
