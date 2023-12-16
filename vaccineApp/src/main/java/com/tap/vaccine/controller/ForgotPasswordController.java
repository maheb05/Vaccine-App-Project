package com.tap.vaccine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.service.ForgotPasswordService;

@Controller
public class ForgotPasswordController {
	
	private ForgotPasswordService forgotPasswordService;
	
	@Autowired
	public ForgotPasswordController(ForgotPasswordService forgotPasswordService){
		this.forgotPasswordService = forgotPasswordService;
	}
	public ForgotPasswordController(){
		System.out.println("Default ForgotPasswordController() ");
	}
	
	@RequestMapping(value = "/forgetPassword")
	public String goToForgetPassword() {
		return "/WEB-INF/forgetPassword.jsp";
	}
	
	@RequestMapping(value = "/enterEmailToResetPassword", method = RequestMethod.POST)
	public String  resetPassword(@RequestParam String email,
			@RequestParam String newPassword,
			@RequestParam String confirmNewPassword,Model model) {
		
		System.out.println("Updating Forget Password Controller");
		boolean userData = forgotPasswordService.validateUserData(email, newPassword);
		if(userData) {
			boolean resetPassword = forgotPasswordService.resetPassword(email, confirmNewPassword);
			if(resetPassword) {
				boolean updatedEmail = forgotPasswordService.passwordUpdatedEmail(email, confirmNewPassword);
				if(updatedEmail) {
					model.addAttribute("emailUpdate", "Email has been sent to your email, Check your email for confirmation");
					System.out.println("Email Sent to "+email+" with updated password"+newPassword);
				}else {
					System.out.println("Email not sent");
					model.addAttribute("emailUpdate", "Email sending failed due to week internet connection , but ");
				}
				
				model.addAttribute("passwordMessage", "Password Reset Successfull, try to login");
				System.out.println("Password Reset Successfull");
			}else {
				model.addAttribute("passwordMessage", "Password Reset Un-Successfull");
				System.out.println("Password Reset Un - Successfull");
			}
		}else {
			model.addAttribute("passwordMessage", email+" not Found try with valid email");
		}
		return "forward:/WEB-INF/forgetPassword.jsp";
	}
	
	@RequestMapping(value = "/goCheckLogin")
	public String goToLogin(Model model) {
		return "forward:/WEB-INF/login.jsp";
	}
}
