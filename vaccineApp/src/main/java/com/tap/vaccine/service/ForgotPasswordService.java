package com.tap.vaccine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.dao.ForgotPasswordDAO;

@Component
public class ForgotPasswordService {
	private ForgotPasswordDAO forgotPasswordDAO;
	
	@Autowired
 	public ForgotPasswordService(ForgotPasswordDAO forgotPasswordDAO){
		this.forgotPasswordDAO = forgotPasswordDAO;
	}
	
	public ForgotPasswordService(){
		System.out.println("Default ForgotPasswordService()");
	}
	
	public boolean validateUserData(String email, String newPassword) {
		
		boolean flag = true;
		
		if(email == null || email.isBlank() || email.isEmpty()) {
			flag = false;
		}
		
		if(newPassword == null || newPassword.isBlank() || newPassword.isEmpty()) {
			flag = false;
		}
		
		return flag;
	}
	
	public boolean resetPassword(String email,String newPassword) {
		return forgotPasswordDAO.resetPassword(email, newPassword);
	}
	
	public boolean passwordUpdatedEmail(String email, String newPassword) {
		return forgotPasswordDAO.passwordUpdatedEmail(email, newPassword);
	}
}
