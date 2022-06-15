package com.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.ApartmentDto;
import com.happyhouse.model.DongDto;
import com.happyhouse.model.GugunDto;
import com.happyhouse.model.MostDealApartmentDto;
import com.happyhouse.model.SidoDto;
import com.happyhouse.model.service.ApartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/apartment")
@Slf4j
@Api("아파트정보 컨트롤러V1")
public class ApartmentController {

	@Autowired
	private ApartmentService apartmentService;

	@ApiOperation(value = "시도 목록", notes = "전국의 <big><strong>시도 목록</strong></big>을 리턴.")
	@ApiResponses({ @ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
			@ApiResponse(code = 500, message = "서버에러!!!"), @ApiResponse(code = 200, message = "시도 목록 정상 처리") })
	@GetMapping("/sido")
	public List<SidoDto> sido() {
		List<SidoDto> sidoList = apartmentService.sidoList();
		log.debug("시도 리스트 : {}", sidoList);

		return sidoList;
	}

	@ApiOperation(value = "구군 목록", notes = "해당 시도의 <big><strong>구군 목록</strong></big>을 리턴.")
	@ApiResponses({ @ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
			@ApiResponse(code = 500, message = "서버에러!!!"), @ApiResponse(code = 200, message = "구군 목록 정상 처리") })
	@GetMapping("/gugun/{sido}")
	public List<GugunDto> gugun(@PathVariable String sido) {
		log.debug("선택된 시도 : {}", sido);
		List<GugunDto> gugunList = apartmentService.gugunList(sido);
		log.debug("구군 리스트 : {}", gugunList);
		return gugunList;
	}

	@ApiOperation(value = "동 목록", notes = "해당 구군의 <big><strong>동 목록</strong></big>을 리턴.")
	@ApiResponses({ @ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
			@ApiResponse(code = 500, message = "서버에러!!!"), @ApiResponse(code = 200, message = "동 목록 정상 처리") })
	@GetMapping("/dong/{sido}/{gugun}")
	public List<DongDto> dong(@PathVariable Map<String, String> sidoGugun) {
		log.debug("선택된 시도 : {} , 구군 : {}", sidoGugun.get("sido"), sidoGugun.get("gugun"));
		List<DongDto> dongList = apartmentService.dongList(sidoGugun);
		log.debug("동 리스트 : {}", dongList);
		return dongList;
	}

	@ApiOperation(value = "아파트 목록", notes = "해당 조건의 <big><strong>아파트 목록</strong></big>을 리턴.")
	@ApiResponses({ @ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
			@ApiResponse(code = 500, message = "서버에러!!!"), @ApiResponse(code = 200, message = "아파트 목록 정상 처리") })
	@GetMapping("/list/{sido}/{gugun}/{dong}/{year}")
	public List<ApartmentDto> apartDealList(@PathVariable Map<String, String> address) {
		log.debug("선택된 시도: {}, 구군 : {}, 동 : {}, 연도: {}", address.get("sido"), address.get("gugun"), address.get("dong"),
				address.get("year"));
		List<ApartmentDto> apartDealList = apartmentService.apartDealList(address);
//		address.forEach((key, value) -> {
//			log.debug(key + " : " + value);
//		});
		for (ApartmentDto apartmentDto : apartDealList) {
			log.debug("가격: ", apartmentDto.getDealAmount());
		}
		log.debug("아파트 실거래 리스트 : {}", apartDealList);
		return apartDealList;
	}

	@ApiOperation(value = "아파트 검색 목록", notes = "<big><strong>검색한 이름을 포함하는 아파트 목록</strong></big>을 리턴.")
	@ApiResponses({ @ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
			@ApiResponse(code = 500, message = "서버에러!!!"), @ApiResponse(code = 200, message = "검색한 아파트 목록 정상 처리") })
	@GetMapping("/list/{sido}/{gugun}/{dong}/{year}/{apart}")
	public List<ApartmentDto> apartList(@PathVariable Map<String, String> address) {
		log.debug("선택된 아파트 이름 : {} ", address.get("apart"));
		List<ApartmentDto> apartDealList = apartmentService.apartList(address);
//		address.forEach((key, value) -> {
//			log.debug(key + " : " + value);
//		});
//		for (ApartmentDto apartmentDto : apartDealList) {
//			log.debug("가격: ", apartmentDto.getDealAmount());
//		}
		log.debug("아파트 리스트 : {}", apartDealList);
		return apartDealList;
	}

	@GetMapping("/maxdeal")
	public ResponseEntity<?> maxDealAmount() {
		log.debug("거래 최댓 값 리턴");
		return new ResponseEntity<ApartmentDto>(apartmentService.maxApartDeal(), HttpStatus.OK);
	}

	@GetMapping("/dealcnt")
	public ResponseEntity<?> dealCount() {
		log.debug("거래 개수 값 리턴");
		return new ResponseEntity<Integer>(apartmentService.dealCount(), HttpStatus.OK);
	}

	@GetMapping("/mostdealapart")
	public ResponseEntity<?> mostDealApartment() {
		log.debug("가장 거래 많은 아파트 리턴");
		MostDealApartmentDto mostDealCntApartment = apartmentService.mostDealCntApartment();
		log.debug("아파트 : {}", mostDealCntApartment);
		return new ResponseEntity<MostDealApartmentDto>(mostDealCntApartment, HttpStatus.OK);
	}

	@GetMapping("/mostdealapart/{top}")
	public ResponseEntity<?> mostDealApartment(@PathVariable Integer top) {
		log.debug("가장 거래 많은 top X 아파트 리턴");
		List<MostDealApartmentDto> mostDealCntApartment = apartmentService.topDealCntApartment(top);
		log.debug("아파트 : {}", mostDealCntApartment);
		return new ResponseEntity<List<MostDealApartmentDto>>(mostDealCntApartment, HttpStatus.OK);
	}

	@GetMapping("/dealcnt/month")
	public ResponseEntity<?> dealCountEachMonth() {
		log.debug("달마다 거래 개수 값 리턴");
		List<Map<String, Object>> dealCntEachMonth = apartmentService.dealCntEachMonth();
//		for (Map<String, Object> map : dealCntEachMonth) {
//			for (String strKey : map.keySet()) {
//				Object strValue = map.get(strKey);
//				log.debug("key : value = {} : {}", strKey, strValue);
//			}
//		}

		return new ResponseEntity<List<Map<String, Object>>>(dealCntEachMonth, HttpStatus.OK);
	}

	@GetMapping("/squarefeet/rank")
	public ResponseEntity<?> rankBySquareFeet() {
		log.debug("평당 랭크  리턴");
		List<Map<String, Object>> rankBysquareFeet = apartmentService.rankBysquarFeet();
//		for (Map<String, Object> map : rankBysquareFeet) {
//			for (String strKey : map.keySet()) {
//				Object strValue = map.get(strKey);
//				log.debug("key : value = {} : {}", strKey, strValue);
//			}
//		}
		return new ResponseEntity<List<Map<String, Object>>>(rankBysquareFeet, HttpStatus.OK);
	}
}
