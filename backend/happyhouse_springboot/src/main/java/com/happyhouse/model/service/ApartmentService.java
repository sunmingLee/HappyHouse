package com.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.happyhouse.model.ApartmentDto;
import com.happyhouse.model.DongDto;
import com.happyhouse.model.GugunDto;
import com.happyhouse.model.MostDealApartmentDto;
import com.happyhouse.model.SidoDto;
import com.happyhouse.model.StoreDto;

public interface ApartmentService {
	List<SidoDto> sidoList();

	List<GugunDto> gugunList(String sidoDto);

	List<DongDto> dongList(Map<String, String> sidoGugun);

	List<ApartmentDto> apartDealList(Map<String, String> address);

	List<StoreDto> nearbyStoreList(Map<String, String> address);
	
	List<StoreDto> curPosNearbyStoreList(Map<String, String> address);

	List<ApartmentDto> apartList(Map<String, String> address);

	ApartmentDto maxApartDeal();
	
	int dealCount();
	
	MostDealApartmentDto mostDealCntApartment();
	
	List<MostDealApartmentDto> topDealCntApartment(Integer top);
	
	List<Map<String, Object>> dealCntEachMonth();
	
	List<Map<String,Object>> rankBysquarFeet();
}
