package com.tap.vaccine.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tap.vaccine.entity.MemberEntity;

@Component
public interface MemberDAO {
	boolean saveMemberEntity(MemberEntity entity);
	List<MemberEntity> viewAllMembers(String userEmail);
	int updateMemberCount(String email,int memberCount);
	int getMemberCount(String email);
}
