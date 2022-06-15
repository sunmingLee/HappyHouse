package com.happyhouse.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.model.ApartmentDto;
import com.happyhouse.model.DongDto;
import com.happyhouse.model.GugunDto;
import com.happyhouse.model.MostDealApartmentDto;
import com.happyhouse.model.NewsDto;
import com.happyhouse.model.SidoDto;
import com.happyhouse.model.StoreDto;
import com.happyhouse.model.mapper.ApartmentMapper;

@Service
public class ApartmentServiceImpl implements ApartmentService {

	@Autowired
	private ApartmentMapper apartmentMapper;

	@Override
	public List<SidoDto> sidoList() {
		List<SidoDto> list = apartmentMapper.findSido();
		return list;
	}

	@Override
	public List<GugunDto> gugunList(String sidoDto) {
		List<GugunDto> list = apartmentMapper.findGugun(sidoDto);
		return list;
	}

	@Override
	public List<DongDto> dongList(Map<String, String> sidoGugun) {
		List<DongDto> list = apartmentMapper.findDong(sidoGugun);
		return list;
	}

	@Override
	public List<ApartmentDto> apartDealList(Map<String, String> address) {
		List<ApartmentDto> list = apartmentMapper.findApartDeal(address);
		return list;
	}

	@Override
	public List<StoreDto> nearbyStoreList(Map<String, String> address) {
		List<StoreDto> list = apartmentMapper.findNearbyStore(address);
		return list;
	}

	@Override
	public List<ApartmentDto> apartList(Map<String, String> address) {

		List<ApartmentDto> list = apartmentMapper.findApart(address);
		return list;
	}

	@Override
	public List<StoreDto> curPosNearbyStoreList(Map<String, String> address) {
		List<StoreDto> list = apartmentMapper.findCurPosNearbyStore(address);
		return list;
	}

	@Override
	public ApartmentDto maxApartDeal() {
		
		return apartmentMapper.maxApartDeal();
	}

	@Override
	public int dealCount() {
		return apartmentMapper.dealCount();
	}

	@Override
	public MostDealApartmentDto mostDealCntApartment() {
		return apartmentMapper.mostDealCntApartment();
	}

	@Override
	public List<Map<String, Object>> dealCntEachMonth() {
		// TODO Auto-generated method stub
		return apartmentMapper.dealCntEachMonth();
	}

	@Override
	public List<MostDealApartmentDto> topDealCntApartment(Integer top) {
		// TODO Auto-generated method stub
		return apartmentMapper.topDealCntApartment(top);
	}

	@Override
	public List<Map<String, Object>> rankBysquarFeet() {
		// TODO Auto-generated method stub
		return apartmentMapper.rankBysquarFeet();
	}

}
