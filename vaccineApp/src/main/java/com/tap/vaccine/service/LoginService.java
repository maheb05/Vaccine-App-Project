package com.tap.vaccine.service;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tap.vaccine.dao.LoginDAO;
import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.EntityNotFoundException;
import com.tap.vaccine.exception.InvalidException;

@Component
public class LoginService {
	
	private static final int maxAttempts = 3;
	public LoginService() {
		System.out.println("Default Login Service");
	}
	
	private LoginDAO loginDAO;
	
	@Autowired
	public LoginService(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}
	
	
	public boolean validateloginCredentials(String email,String password) throws InvalidException ,EntityNotFoundException{
		System.out.println("Invoked Validate Login credentials service ");
		boolean flag = true;
		
		if(email == null || email.isBlank() || email.isEmpty()) {
			flag = false;
			throw new InvalidException("Please enter valid email");
		}
		
		if(password == null || password.isEmpty() || password.isBlank()) {
			flag = false;
			throw new InvalidException("please enter valid password");
		}
		return flag;
	}
	
	public boolean verifyLoginCredentials(String email,String password) throws InvalidException, EntityNotFoundException {
		boolean credentials = validateloginCredentials(email, password);
		if (credentials) {
            RegisterEntity entity = loginDAO.getRegisterEntityByEmail(email);

            if (entity != null) {
            	if(entity.getLoginAttempts()> maxAttempts) {
            		System.out.println("Account Blocked");
            		throw new InvalidException("This account is blocked check your mail and reset the password, login again");
            	}
            	
               if(entity.getPassword().equals(password)) {
            	   return true;
               }
               else {
            	   int attempts = entity.getLoginAttempts()+1;
            	   loginDAO.updateRegisterEntity(email, attempts);
            	   System.out.println("Unsuccessfull login incorrect password");
            	   if(attempts> maxAttempts) {
            		   boolean blockingEmail = loginDAO.sendAccountBlockingEmail(email, password);
            		   if(blockingEmail) {
            			   System.out.println("account blocking mail sent,, blocked due to multipe attempts");
            			   throw new InvalidException("your account is blocked due multiple incorrect login credentials please check your mail and reset the the password, login again");
            		   }else {
            			   System.out.println("account blocking mail not sent");
            			   throw new InvalidException("Email could not able to send due to weak internet connect");
            		   }
            	   }
            	   throw new InvalidException("Incorrect password ");
               }
            } 
            else {
                throw new InvalidException("Email doesn't exist. Please register.");
            }
        } else {
            return false;
        }
    }
	
	public RegisterEntity getRegisterEntityByEmail(String email) throws EntityNotFoundException {
		return loginDAO.getRegisterEntityByEmail(email);
	}
}