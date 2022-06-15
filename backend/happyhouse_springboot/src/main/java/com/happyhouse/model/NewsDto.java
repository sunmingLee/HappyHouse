package com.happyhouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
	private String title;
	private String url;
	private String body;
	private String source;
	private String date;
	
}
