package com.happyhouse.model.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.UserDto;

@Mapper
public interface UserMapper {
	public int idCheck(String id);

	public int registerUser(UserDto userDto);

	public UserDto login(Map<String, String> map);

	public UserDto findById(String id);
//
	public int updateUser(UserDto userDto);

	public boolean deleteUser(String id);
	
	public UserDto findByUserName(String username);
	
//	List<UserDto> searchAll(); 
}
