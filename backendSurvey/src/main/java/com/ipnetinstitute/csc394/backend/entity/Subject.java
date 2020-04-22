package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor@NoArgsConstructor
@Data
public class Subject extends BaseEntity {
	
	String code;
	String name;
	
	@OneToMany(mappedBy = "term", cascade = {CascadeType.ALL} )
	private List <Course> courses = new ArrayList<Course>();

}
