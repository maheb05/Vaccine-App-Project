package com.tap.vaccine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LogoutController {
	LogoutController(){
		System.out.println("Default Logout Controller");
	}
	
	@GetMapping("/logoutButton")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
        	String n =(String) session.getAttribute("userEmail");
            session.invalidate();
        }
        return "forward:/WEB-INF/login.jsp";
    }
}
