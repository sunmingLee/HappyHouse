package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.AnnouncementDto;
import com.happyhouse.model.NewsDto;
import com.happyhouse.model.service.AnnouncementService;
import com.happyhouse.model.service.ApartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/announcements")
@Slf4j
@Api("공지사항 컨트롤러V1")
public class AnnouncementController {

	@Autowired
	private AnnouncementService announcementService;

	@ApiOperation(value = "공지사항 목록", notes = "공지사항의<big><strong>페이지별 목록</strong></big>을 리턴.")
	@ApiResponses({ @ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
			@ApiResponse(code = 500, message = "서버에러!!!"), @ApiResponse(code = 200, message = "공지사항 목록 정상 처리") })

	@GetMapping("/list")
	public List<AnnouncementDto> annoucementList() {
		List<AnnouncementDto> announcementList = announcementService.announcementList();
		log.debug("공지사항 목록 : {}", announcementList);

		return announcementList;
	}

	@ApiOperation(value = "공지사항 하나 보기", notes = "공지사항의<big><strong>상세 정보 1개</strong></big>를 리턴.")
	@ApiResponses({ @ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
			@ApiResponse(code = 500, message = "서버에러!!!"), @ApiResponse(code = 200, message = "공지사항 상세정보 정상 처리") })
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/announcement/{announcementNo}")
	public AnnouncementDto annoucementInfo(@PathVariable("announcementNo") int announcementNo) {
		AnnouncementDto announcementInfo = announcementService.AnnouncementInfo(announcementNo);
		log.debug("annoucementInfo : {}", announcementInfo);

		return announcementInfo;
	}

	@ApiOperation(value = "공지사항 글등록", notes = "새로운 공지사항 글 등록.")
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/write")
	public void writeAnnouncement(@RequestBody AnnouncementDto announcementDto) {
		log.debug("writeAnnouncemen : {}", announcementDto);
		announcementService.writeAnnouncement(announcementDto);
	}

	@ApiOperation(value = "공지사항 글정보 수정", notes = "글번호에 해당하는 공지사항의 정보를 수정한다.")
	@Secured({"ROLE_ADMIN"})
	@PutMapping("{announcementNo}")
	public void modifyAnnouncement(@RequestBody AnnouncementDto announcementDto) {
		log.debug("modifyAnnouncement : {}", announcementDto);
		announcementService.modifyAnnouncement(announcementDto);
	}

	@ApiOperation(value = "공지사항 글삭제", notes = "글번호에 해당하는 공지사항 글을 삭제한다.")
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("{announcementNo}")
	public void deleteAnnouncement(@PathVariable int announcementNo) {
		log.debug("deleteAnnouncement : {}", announcementNo);
		announcementService.deleteAnnouncement(announcementNo);
	}
}
