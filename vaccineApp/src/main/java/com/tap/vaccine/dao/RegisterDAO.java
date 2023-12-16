package com.tap.vaccine.dao;

import com.tap.vaccine.entity.RegisterEntity;

public interface RegisterDAO {
	boolean saveRegisterEntity(RegisterEntity entity);
	boolean userLogin(String email,String password);
	boolean sendRegistrationEmail(String username,String email,String password);
}
