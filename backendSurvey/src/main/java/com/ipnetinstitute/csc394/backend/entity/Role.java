package com.ipnetinstitute.csc394.backend.entity;

import javax.persistence.*;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

@Entity


public class Role  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_user", nullable = false, unique = true)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "id_user", nullable = false, unique = true)
	// private User user;

}


