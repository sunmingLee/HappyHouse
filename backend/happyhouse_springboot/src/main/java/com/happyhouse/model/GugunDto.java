package com.happyhouse.model;

import lombok.Data;

@Data
public class GugunDto {
	private String gugun;

	public GugunDto(String gugun) {
		super();
		this.gugun = gugun;
	}
	
	
}
