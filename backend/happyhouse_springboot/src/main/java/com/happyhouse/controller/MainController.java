package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.NewsDto;
import com.happyhouse.model.service.NewsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MainController {

	@Autowired
	private NewsService newsService;
	
	
	@ApiOperation(value = "뉴스 목록", notes = "크롤링한 뉴스 중 상단의 5개 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "뉴스 크롤링 정상 처리") 
	})
	@GetMapping("/newscrawling")
	public ResponseEntity<List<NewsDto>> newsList() {
		List<NewsDto> newsList = newsService.newsList();
		log.debug("newsList :{}", newsList.toArray());
		return new ResponseEntity<List<NewsDto>>(newsList, HttpStatus.OK);
	}
}
