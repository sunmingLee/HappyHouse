package com.happyhouse.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	private String id;
	private String user_password;
	private String user_name;
	private String user_email;
	private String user_tel;
	private String roles;	// USER, ADMIN
	private String address;
	private String aboutme;

	public List<String> getRoleList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<String>();
	}
}
