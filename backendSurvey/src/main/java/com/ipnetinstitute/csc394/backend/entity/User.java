package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User extends BaseEntity {
	String firstName;
	String lastName;
	String phone;
	String eMail;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
	private List<Student> student= new ArrayList<Student>();
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
	private List<Teacher> teacher= new ArrayList<Teacher>();
	
	
	 @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	 private List<Role> roles;
	

}
