package com.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.DongDto;
import com.happyhouse.model.GugunDto;
import com.happyhouse.model.SidoDto;
import com.happyhouse.model.StoreDto;
import com.happyhouse.model.service.ApartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/nearbystore")
@Slf4j
@Api("상가 컨트롤러V1")
public class NearbyStoreController {

	@Autowired
	private ApartmentService apartmentService;

	@ApiOperation(value = "시도 목록", notes = "전국의 <big><strong>시도 목록</strong></big>을 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "시도 목록 정상 처리") 
	})
	@GetMapping("/sido")
	public List<SidoDto> sido() {
		List<SidoDto> sidoList = apartmentService.sidoList();
		log.debug("시도 리스트 : {}", sidoList);

		return sidoList;
	}

	@ApiOperation(value = "구군 목록", notes = "해당 시도의 <big><strong>구군 목록</strong></big>을 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "구군 목록 정상 처리") 
	})
	@GetMapping("/gugun/{sido}")
	public List<GugunDto> gugun(@PathVariable String sido) {
		log.debug("선택된 시도 : {}", sido);
		List<GugunDto> gugunList = apartmentService.gugunList(sido);
		log.debug("구군 리스트 : {}", gugunList);
		return gugunList;
	}
	
	@ApiOperation(value = "동 목록", notes = "해당 구군의 <big><strong>동 목록</strong></big>을 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "동 목록 정상 처리") 
	})
	@GetMapping("/dong/{sido}/{gugun}")
	public List<DongDto> dong(@PathVariable Map<String, String> sidoGugun) {
		log.debug("선택된 시도 : {} , 구군 : {}", sidoGugun.get("sido"), sidoGugun.get("gugun"));
		List<DongDto> dongList = apartmentService.dongList(sidoGugun);
		log.debug("동 리스트 : {}", dongList);
		return dongList;
	}

	@ApiOperation(value = "분야별 상가 목록", notes = "해당 동의 <big><strong>분야별 상가 목록</strong></big>을 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "분야별 상가 목록 정상 처리") 
	})
	// field의 특수문자 처리
	@GetMapping("/list/{sido}/{gugun}/{dong}/{field}")
	public List<StoreDto> storeList(@PathVariable Map<String, String> address) {
		log.debug("시도: {}, 구군 : {}, 동 : {}, 분야: {}", address.get("sido"), address.get("gugun"), address.get("dong"),
				address.get("field"));
		List<StoreDto> nearbyStoreList = apartmentService.nearbyStoreList(address);
		log.debug("인근 상가 리스트 : {}", nearbyStoreList);
		return nearbyStoreList;
	}

	@ApiOperation(value = "주변 상가 목록", notes = "<big><strong>주변의 상가 목록</strong></big>을 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "주변 상가 목록 정상 처리") 
	})
	@GetMapping("/list/{sido}/{gugun}/{dong}")
	public List<StoreDto> curPosStoreList(@PathVariable Map<String, String> address) {
		log.debug("시도: {}, 구군 : {}, 동 : {}", address.get("sido"), address.get("gugun"), address.get("dong"));
		List<StoreDto> curPosNearbyStoreList = apartmentService.curPosNearbyStoreList(address);
		log.debug("인근 상가 리스트 : {}", curPosNearbyStoreList);
		return curPosNearbyStoreList;
	}

}
