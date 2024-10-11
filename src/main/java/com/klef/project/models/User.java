package com.klef.project.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user_table")
public class User implements Serializable
{
	@Column(name="user_name",nullable = false,length = 50)
	private String name;
	@Column(name="user_gender",nullable = false,length = 10)
	private String gender;
	@Id
	@Column(name="user_email",nullable = false,length = 50,unique = true)
	private String email;
	@Column(name="user_password",nullable = false,length = 50)
	private String password;
	@Column(name="user_contact",nullable = false,length = 20,unique = true)
	private String contact;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
