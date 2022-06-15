package com.happyhouse.model;

import lombok.Data;

@Data
public class DongDto {
	private String dong;

	public DongDto(String dong) {
		super();
		this.dong = dong;
	}
}
