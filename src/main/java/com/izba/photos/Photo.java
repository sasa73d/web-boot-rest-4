package com.izba.photos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="photos")
public class Photo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="album_id")
	private Long albumId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="url")
	private String url;
	
	@Column(name="thumbnail_url")
	private String thumbnailUrl;
	
	public Photo() {
		
	}

	public Photo(Long id, Long albumId, String title, String url, String thumbnailUrl) {
		this.id = id;
		this.albumId = albumId;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

}

