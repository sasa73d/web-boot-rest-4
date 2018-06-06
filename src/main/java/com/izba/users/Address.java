package com.izba.users;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="street")
	private String street;
	
	@Column(name="suite")
	private String suite;
	
	@Column(name="city")
	private String city;
	
	@Column(name="zipcode")
	private String zipcode;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="geo_id")
	private Geo geo;
	
	public Address() {
		
	}

	public Address(Integer id, String street, String suite, String city, String zipcode, Geo geo) {
		this.id = id;
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.geo = geo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Geo getGeo() {
		return geo;
	}

	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	
}

