package com.tap.vaccine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.EntityNotFoundException;
import com.tap.vaccine.exception.InvalidException;
import com.tap.vaccine.service.LoginService;

@Controller
public class LoginController {
	
	public static String userEmail;
	
	private LoginService loginService;
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public LoginController() {
		System.out.println("Default Login Controller");
	}
	
	
	
	
	@RequestMapping(value = "/loginPage")
	public String gotoLoginPage(Model model) {
		return "/WEB-INF/login.jsp";
	}
	
	@RequestMapping(value = "/getLoginPage")
	public String getLogin() {
		return "forward:/WEB-INF/login.jsp";
	}
	
	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	public String onLogin(@RequestParam String email ,@RequestParam String password, Model model,HttpServletRequest request) {
		System.out.println("Login Controlller ");
		
		try {
			boolean verify = loginService.verifyLoginCredentials(email, password);
			
			if(verify) {
				model.addAttribute("loginSuccess", "Login Succesfull");
				HttpSession session = request.getSession(true);
				session.setAttribute("userEmail", email);
				userEmail =(String) session.getAttribute("userEmail");
				RegisterEntity entity = loginService.getRegisterEntityByEmail(email);
				model.addAttribute("memCount", entity.getMemberCount());
				return "/WEB-INF/homepage.jsp";
			}else {
				model.addAttribute("loginSuccessFailure", "Invalid Credentials");
				return "forward:/WEB-INF/login.jsp";
			}
		}
		catch(InvalidException | EntityNotFoundException e) {
			model.addAttribute("response", e.getMessage());
			model.addAttribute("responseClass", "failure");
		}
		
		return "/WEB-INF/login.jsp";
	}
}
