package com.izba.albums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="albums")
public class Album {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long userId;
	private String title;
	
	public Album() {
	
	}

	public Album(Long id, Long userId, String title) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
