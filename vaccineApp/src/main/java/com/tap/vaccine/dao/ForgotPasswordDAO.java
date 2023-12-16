package com.tap.vaccine.dao;

public interface ForgotPasswordDAO {
	boolean resetPassword(String email,String newPassword);
	boolean passwordUpdatedEmail(String email,String newPassword);
}
