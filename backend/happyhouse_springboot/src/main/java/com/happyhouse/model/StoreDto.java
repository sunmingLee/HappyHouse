package com.happyhouse.model;

import lombok.Data;

@Data
public class StoreDto {
	private String storeName;
	private String storeMinorName;
	private String jibunAddress;
	private String lng;
	private String lat;
	
	public StoreDto(String storeName, String storeMinorName, String jibunAddress, String lng, String lat) {
		super();
		this.storeName = storeName;
		this.storeMinorName = storeMinorName;
		this.jibunAddress = jibunAddress;
		this.lng = lng;
		this.lat = lat;
	}
}
