package com.happyhouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MostDealApartmentDto {
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
	private int cnt;
}
