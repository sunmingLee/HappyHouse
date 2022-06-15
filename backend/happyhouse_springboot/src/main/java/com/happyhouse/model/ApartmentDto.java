package com.happyhouse.model;

import lombok.Data;

@Data
public class ApartmentDto {
	private int aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private int buildYear;
	private String jibun;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String dealAmount;
	private int floor;
	private double lat;
	private double lng;
}
