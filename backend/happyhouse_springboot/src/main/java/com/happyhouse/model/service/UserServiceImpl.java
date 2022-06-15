package com.happyhouse.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhouse.model.UserDto;
import com.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int idCheck(String id) {
		return userMapper.idCheck(id);
	}
 
	@Override
	@Transactional
	public int registerUser(UserDto userDto) throws Exception {
		return userMapper.registerUser(userDto);
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return userMapper.login(map);
	}

	@Override
	@Transactional
	public int updateUser(UserDto userDto) throws Exception {
		return userMapper.updateUser(userDto);
	}

	@Override
	@Transactional
	public void deleteUser(String id) throws Exception {
		userMapper.deleteUser(id);
	}

	@Override
	public UserDto findById(String id) throws Exception {
		return userMapper.findById(id);
	}
}
