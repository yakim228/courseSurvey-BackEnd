package com.ipnetinstitute.csc394.backend.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User extends BaseEntity {
	


	String firstName;
	String lastName;
	String userName;
	String phone;
	String email;
	String password;

	// public User() {
	// }
	public User(String firstName, String lastName, String userName, String phone, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	



	// public String getFirstName() {
	// 	return firstName;
	// }

	// public void setFirstName(String firstName) {
	// 	this.firstName = firstName;
	// }

	// public String getLastName() {
	// 	return lastName;
	// }

	// public void setLastName(String lastName) {
	// 	this.lastName = lastName;
	// }

	// public String getPhone() {
	// 	return phone;
	// }

	// public void setPhone(String phone) {
	// 	this.phone = phone;
	// }

	// public String geteMail() {
	// 	return email;
	// }

	// public void seteMail(String email) {
	// 	this.email = email;
	// }
	
	
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
	private List<Student> student= new ArrayList<Student>();
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
	private List<Teacher> teacher= new ArrayList<Teacher>();
	
	
	 @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private Set<Role> role = new HashSet<>();
	

	// public Set<String> getRole() {
	// 	return this.roles;
	// }

	// public void setRole(Set<String> role) {
	// 	this.role = role;
	// }

}
