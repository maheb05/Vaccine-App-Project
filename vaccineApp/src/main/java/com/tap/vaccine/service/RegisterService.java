package com.tap.vaccine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.dao.RegisterDAO;
import com.tap.vaccine.entity.RegisterEntity;

@Component
public class RegisterService {
	
	public RegisterService() {
		System.out.println("This Is Default Register Service");
	}
	
	private RegisterDAO registerDAO;
	
	@Autowired
	public RegisterService(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}
	
	public boolean validateDetails( String username, String email,String password,long mobileNumber,String gender,String dob) {
		boolean flag = false;
		
		if(username != null && !username.isEmpty() && !username.isBlank()) {
			flag = true;
		}else {
			flag = false;
		}
		
		if(email != null && !email.isEmpty() && !email.isBlank()) {
			flag = true;
		}else {
			flag = false;
		}
		
		if(password != null && !password.isEmpty() && !password.isBlank()) {
			flag = true;
		}else {
			flag = false;
		}
		
		if(mobileNumber > 0 && String.valueOf(mobileNumber).length()==10) {
			flag = true;
		}else {
			flag = false;
		}
		
		if(gender != null && !gender.isBlank() && !gender.isEmpty()) {
			flag = true;
		}else {
			flag = false;
		}
		
		if(dob != null && !dob.isBlank() && !dob.isEmpty()) {
			flag = true;
		}else {
			flag = false;
		}
		
		if(flag) {
			RegisterEntity entity = new RegisterEntity(username, email, password, mobileNumber, gender, dob);
			boolean saveRegisterEntity = registerDAO.saveRegisterEntity(entity);
			return saveRegisterEntity;
		}
		return flag;
	}
	
	public boolean sendRegistrationEmail(String Username,String email,String password) {
		return registerDAO.sendRegistrationEmail(Username, email, password);
	}
}
