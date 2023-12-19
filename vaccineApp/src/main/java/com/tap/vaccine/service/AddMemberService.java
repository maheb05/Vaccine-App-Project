package com.tap.vaccine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.controller.LoginController;
import com.tap.vaccine.controller.LogoutController;
import com.tap.vaccine.dao.MemberDAO;
import com.tap.vaccine.entity.MemberEntity;

@Component
public class AddMemberService {
	
	private MemberDAO memberDAO;
	
	public AddMemberService() {
		System.out.println("Default "+this.getClass().getSimpleName());
	}
	
	@Autowired
	AddMemberService(MemberDAO memberDAO){
		this.memberDAO = memberDAO;
	}
	
	String email = LoginController.userEmail;
	public boolean validateMemberData(String memberName,String gender,String dob, String governamentId,long idProof,String vaccineType,int dose,String email) {
		boolean flag = false;
		
		if(memberName != null && !memberName.isBlank() && !memberName.isEmpty()) {
			System.out.println("Name is valid");
			flag = true;
		}else {
			System.out.println("Name is invalid");
			flag = false;
		}
		
		if(gender != null && !gender.isEmpty() && !gender.isBlank()) {
			System.out.println("gender is valid");
			flag = true;
		}else {
			System.out.println("gender is invalid");
			flag = false;
		}
		
		if(dob != null && !dob.isBlank() && !dob.isEmpty()) {
			System.out.println("dob is valid");
			flag = true;
		}else {
			System.out.println("dob is invalid");
			flag = false;
		}
		
		if(governamentId != null && !governamentId.isEmpty() && !governamentId.isBlank()) {
			System.out.println("GI is valid");
			flag = true;
		}else {
			System.out.println("Gi is invalid");
			flag = false;
		}
		
		if(idProof > 0) {
			System.out.println("iDP is valid");
			flag = true;
		}else {
			System.out.println("IDp is invalid");
			flag = false;
		}
		
		if(vaccineType != null && !vaccineType.isBlank() && !vaccineType.isEmpty()) {
			System.out.println("VT is valid");
			flag = true;
		}else {
			System.out.println("Vt is invalid");
			flag = false;
		}
		
		if(dose > 0) {
			System.out.println("dose is valid");
			flag = true;
		}else {
			System.out.println("Name is valid");
			flag = false;
		}
		
		System.out.println(flag);
		if(flag) {
			MemberEntity entity = new MemberEntity(memberName, gender, dob, governamentId, idProof, vaccineType, dose,email);
			return memberDAO.saveMemberEntity(entity);
		}
		return flag;
	}
	
	public int getMemberCount(String email) {
		return memberDAO.getMemberCount(email);
	}
	
	public boolean checkMemberCount(String email) {
		final int maxMembers = 4;
		int memberCount = memberDAO.getMemberCount(email);
		return memberCount < maxMembers;
	}
	
	public int updateMemberCount(String emai, int memberCount) {
		return memberDAO.updateMemberCount(emai, memberCount);
	}
	
	public List<MemberEntity> getAllMembers(String email){
		return memberDAO.viewAllMembers(email);
	}
	
	public MemberEntity getEntityById(int id) {
		return memberDAO.getEntityById(id);
	}
	
	public boolean updateMemberDetails(int memberId,String memberNamename,String gender,String dob,
			String governmentId,long idProof,String vaccineType,int dose,String email) {
		MemberEntity entity = new MemberEntity(memberId,memberNamename, gender, dob, governmentId, idProof, vaccineType, dose,email);
		return memberDAO.updateMemberEntity(entity);
	}
	
	public boolean deleteMemberEntityById(int id) {
		return memberDAO.deleteMemberEntityById(id);
	}
	
	public int decreaseMemberCount(String email, int memberCount) {
		return memberDAO.decreaseMemberCount(email, memberCount);
	}
}
