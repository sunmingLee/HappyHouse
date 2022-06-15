package com.happyhouse.model.service;

import java.util.Map;

import com.happyhouse.model.UserDto;

public interface UserService {

	int idCheck(String id);
	int registerUser(UserDto userDto) throws Exception;
	UserDto login(Map<String, String> map) throws Exception;
	int updateUser(UserDto userDto) throws Exception;
	void deleteUser(String id) throws Exception;
	UserDto findById(String id) throws Exception;
//	MemberDto infoMember(String id) throws Exception;
	 
}
