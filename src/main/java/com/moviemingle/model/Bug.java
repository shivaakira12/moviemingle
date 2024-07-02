package com.moviemingle.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="bugmessages")
public class Bug {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String bugDescription;
	private String bugPageURL;
	private String image;
	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bug(int id, String bugDescription, String bugPageURL, String image) {
		super();
		this.id = id;
		this.bugDescription = bugDescription;
		this.bugPageURL = bugPageURL;
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBugDescription() {
		return bugDescription;
	}
	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}
	public String getBugPageURL() {
		return bugPageURL;
	}
	public void setBugPageURL(String bugPageURL) {
		this.bugPageURL = bugPageURL;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
