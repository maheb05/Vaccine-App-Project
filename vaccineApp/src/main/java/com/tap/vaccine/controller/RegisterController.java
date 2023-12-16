package com.tap.vaccine.controller;

import java.lang.annotation.Retention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.service.RegisterService;

@Controller
public class RegisterController {
	
	public RegisterController() {
		System.out.println("Default Register Controller");
	}
	
	private RegisterService registerService;
	
	@Autowired
	public RegisterController(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@RequestMapping(value = "/welcomePage")
	public String goRegisterPage(Model model) {
		System.out.println("Register Controller");
		
		model.addAttribute("thisss", "Register yourself");
		return "/WEB-INF/Register.jsp";
	}
	
	@RequestMapping(value = "/registerForm" ,method = RequestMethod.POST) 
	public String register(@RequestParam String username,
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam long mobileNumber,
			@RequestParam String gender,
			@RequestParam String dob,Model model) {
		model.addAttribute("response", "Successful");
		
		System.out.println("Register Page");
		
		boolean validateDetails = registerService.validateDetails(username, email, password, mobileNumber, gender, dob);
		if (validateDetails) {
			boolean registrationEmail = registerService.sendRegistrationEmail(username, email, password);
			if (registrationEmail) {
		        model.addAttribute("mailResponse", "Mail sent, check your Email");
		        model.addAttribute("mailResponseClass", "success");
		        System.out.println("Mail Sent Successfully");
		    } else {
		        model.addAttribute("mailResponse", "Something went wrong while sending Email, Connection Issue");
		        model.addAttribute("mailResponseClass", "failure");
		        System.out.println("Sending Mail unsuccessful");
		    }

            model.addAttribute("response", "Registered Successfully");
            model.addAttribute("responseClass", "success"); 
        } else {
            model.addAttribute("response", "Something Went Wrong. Please try again");
            model.addAttribute("responseClass", "failure"); 
        }
		
		return "forward:/WEB-INF/Register.jsp"; 
	}
	
	
	@RequestMapping(value = "/alreadyHaveAnAccount")
	public String alreadyAccountHave() {
		return "/WEB-INF/login.jsp";
	}
	
	@RequestMapping(value = "/register")
	public String gotoRegister() {
		return "/WEB-INF/Register.jsp";
	}
}

