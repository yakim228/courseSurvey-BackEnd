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
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {

	String firstName;
	String lastName;
	String userName;
	String phone;
	String email;
	String password;


	public User(String firstName, String lastName, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	public User(String firstName, String lastName, String userName, String phone, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Student> student = new ArrayList<Student>();

	@OneToMany(mappedBy = "user", cascade = { CascadeType.ALL })
	private List<Teacher> teacher = new ArrayList<Teacher>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Role> role = new HashSet<>();


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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

<<<<<<< HEAD
	// public void seteMail(String email) {
	// 	this.email = email;
	// }
	
	
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
	private List<Student> student= new ArrayList<Student>();
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.ALL})
	private List<Teacher> teacher= new ArrayList<Teacher>();
	
	
	 @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private Set<Role> role = new HashSet<>();
	
=======
	public String getEmail() {
		return email;
	}
>>>>>>> fdc0442769b4f53d05958c45920a10bf7d43a88f

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}


}
