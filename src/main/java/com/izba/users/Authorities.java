package com.izba.users;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="authority")
public class Authorities {
	
	@Override
	public String toString() {
		return "'" + authoritie + "'";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore
	private Long id;
	
	@Column(name="authority")
	private String authoritie;
	
	/*@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="user_auth",
			joinColumns=@JoinColumn(name="auth_id"),
			inverseJoinColumns=@JoinColumn(name="user_id")
	)*/
	@ManyToMany( mappedBy = "authrities", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<User> users;
	
	public Authorities() {
		
	}

	public Authorities( String authoritie) {
		this.authoritie = authoritie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getAuthoritie() {
		return authoritie;
	}

	public void setAuthoritie(String authoritie) {
		this.authoritie = authoritie;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
