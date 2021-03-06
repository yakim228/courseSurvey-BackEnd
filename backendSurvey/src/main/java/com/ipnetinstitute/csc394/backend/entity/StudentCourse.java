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
@Table(name= "student_course")
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Getter
//@Setter
public class StudentCourse extends BaseEntity{


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_course", nullable = false, referencedColumnName = "id", updatable = false, insertable = false)
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="id_student", nullable = false, referencedColumnName = "id", updatable = false, insertable = false)
	private Student student;

	Integer id_course, id_student;

//	public Course getCourse() {
//		return course;
//	}
//
//	public void setCourse(Course course) {
//		this.course = course;
//	}
//
//	public Student getStudent() {
//		return student;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}

	public Integer getId_course() {
		return id_course;
	}

	public void setId_course(Integer id_course) {
		this.id_course = id_course;
	}

	public Integer getId_student() {
		return id_student;
	}

	public void setId_student(Integer id_student) {
		this.id_student = id_student;
	}
}
