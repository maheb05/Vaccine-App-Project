package com.tap.vaccine.dao;

import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.RegisterEntity;
import com.tap.vaccine.exception.EntityNotFoundException;

@Component
public interface LoginDAO {
	RegisterEntity getRegisterEntityByEmail(String email) throws EntityNotFoundException;
	boolean updateRegisterEntity(String email,int loginAttempts);
	boolean sendAccountBlockingEmail(String email,String username);
}
