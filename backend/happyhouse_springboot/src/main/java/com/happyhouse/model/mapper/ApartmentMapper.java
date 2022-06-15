package com.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.ApartmentDto;
import com.happyhouse.model.DongDto;
import com.happyhouse.model.GugunDto;
import com.happyhouse.model.MostDealApartmentDto;
import com.happyhouse.model.SidoDto;
import com.happyhouse.model.StoreDto;

@Mapper
public interface ApartmentMapper {
	List<SidoDto> findSido();

	List<GugunDto> findGugun(String sidoDto);

	List<DongDto> findDong(Map<String, String> sidoGugun);

	List<ApartmentDto> findApartDeal(Map<String, String> address);

	List<StoreDto> findNearbyStore(Map<String, String> address);
	
	List<StoreDto> findCurPosNearbyStore(Map<String, String> address);

	List<ApartmentDto> findApart(Map<String, String> address);
	
	ApartmentDto maxApartDeal();
	
	int dealCount();
	
	MostDealApartmentDto mostDealCntApartment();
	
	List<MostDealApartmentDto> topDealCntApartment(Integer top);
	
	List<Map<String, Object>> dealCntEachMonth();
	
	List<Map<String,Object>> rankBysquarFeet();
}
