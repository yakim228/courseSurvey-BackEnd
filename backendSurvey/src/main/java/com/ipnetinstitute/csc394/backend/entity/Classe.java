package com.ipnetinstitute.csc394.backend.entity;


import java.util.List;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
public class Classe extends BaseEntity{
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	 * @OneToMany(mappedBy = "classe", cascade = {CascadeType.ALL}) private
	 * List<Course> courses = new ArrayList<>() ;
	 */
}
