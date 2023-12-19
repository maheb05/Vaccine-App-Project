package com.tap.vaccine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_table")
public class MemberEntity {
	
	@Column(name="MEMBER_ID")
	@Id
	private int memberId;
	
	@Column(name = "MEMBER_NAME")
	private String memberName;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "DOB")
	private String dob;
	
	@Column(name = "GOVERNAMENT_ID")
	private String governamentID;
	
	@Column(name = "ID_PROOF")
	private long idProof;
	
	@Column(name = "VACCINE_TYPE")
	private String vaccineType;
	
	@Column(name = "DOSE")
	private int dose;
	
	@Column(name = "USER_EMAIL")
	private String userEmail;
	
	public MemberEntity() {
		System.out.println("Default Member Entity constructor");
	}
	
	public MemberEntity(int memberId, String memberName, String gender, String dob, String governamentID, long idProof,
			String vaccineType, int dose,String userEmail) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.gender = gender;
		this.dob = dob;
		this.governamentID = governamentID;
		this.idProof = idProof;
		this.vaccineType = vaccineType;
		this.dose = dose;
		this.userEmail = userEmail;
	}
	
	public MemberEntity( String memberName, String gender, String dob, String governamentID, long idProof,
			String vaccineType, int dose) {
		super();
		this.memberName = memberName;
		this.gender = gender;
		this.dob = dob;
		this.governamentID = governamentID;
		this.idProof = idProof;
		this.vaccineType = vaccineType;
		this.dose = dose;
	}

	public MemberEntity( String memberName, String gender, String dob, String governamentID, long idProof,
			String vaccineType, int dose,String userEmail) {
		super();
		this.memberName = memberName;
		this.gender = gender;
		this.dob = dob;
		this.governamentID = governamentID;
		this.idProof = idProof;
		this.vaccineType = vaccineType;
		this.dose = dose;
		this.userEmail = userEmail;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
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

	public String getGovernamentID() {
		return governamentID;
	}

	public void setGovernamentID(String governamentID) {
		this.governamentID = governamentID;
	}

	public long getIdProof() {
		return idProof;
	}

	public void setIdProof(long idProof) {
		this.idProof = idProof;
	}

	public String getVaccineType() {
		return vaccineType;
	}

	public void setVaccineType(String vaccineType) {
		this.vaccineType = vaccineType;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		
		return getMemberId()+" "+getMemberName()+" "+getGender()+" "+getDob()+" "+getGovernamentID()+" "+getIdProof()+
				" "+getVaccineType()+" "+getDose();
	}
}
