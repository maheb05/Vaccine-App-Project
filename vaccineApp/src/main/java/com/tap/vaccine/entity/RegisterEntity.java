package com.tap.vaccine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="register_table")
public class RegisterEntity {
	
	@Column(name = "ID")
	@Id
	private int id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name = "MOBILE_NUMBER")
	private long mobileNumber;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "DOB")
	private String dob;
	
	@Column(name="LOGIN_ATTEMPTS")
	private int loginAttempts;
	
	@Column(name = "MEMBER_COUNT")
	private Integer memberCount;
	
	public RegisterEntity() {
		System.out.println("Default Entity Constructor");
	}
	
	public RegisterEntity(String username, String email, String password, long mobileNumber, String gender,
			String dob) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dob = dob;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	@Override
	public String toString() {
		return getId()+" "+getUsername()+" "+getEmail()+" "+getPassword()+" "+getMobileNumber()+" "+getGender()+" "+getDob()+" "+getLoginAttempts();
	}
}
