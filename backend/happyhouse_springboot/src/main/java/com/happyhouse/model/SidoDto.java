package com.happyhouse.model;

import lombok.Data;

@Data
public class SidoDto {
	private String sido;

	public SidoDto(String sido) {
		super();
		this.sido = sido;
	}
	
	
}
