package com.happyhouse.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.ReplyBoardDto;
import com.happyhouse.model.service.ReplyBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
@Slf4j
@Api("Q&A 컨트롤러V1")
public class ReplyBoardController {

	@Autowired
	private ReplyBoardService replyBoardService;

	
	@ApiOperation(value = "Q&A목록", notes = "Q&A의<big><strong>전체 목록</strong></big>을 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "관심사 목록 정상 처리") 
	})
	@GetMapping("/list")
	public List<ReplyBoardDto> replyBoardList() {
		List<ReplyBoardDto> replyBoardList = replyBoardService.replyBoardList();

		log.debug("replyBoardList : {}", replyBoardList);

		return replyBoardList;
	}

	@ApiOperation(value = "Q&A 하나 보기", notes = "Q&A의 <big><strong>상세 정보 1개</strong></big>를 리턴.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "Q&A 상세 정보 정상 처리") 
	})
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/detail/{articleno}")
	public ReplyBoardDto getReplyBoard(@PathVariable Integer articleno) {
		ReplyBoardDto replyBoard = replyBoardService.getReplyBoard(articleno);

		log.debug("replyBoard : {}", replyBoard);

		return replyBoard;
	}

	@ApiOperation(value = "Q&A 새로 등록", notes = "<big><strong>새로운 Q&A</strong></big>를 등록.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "Q&A 등록 정상 처리") 
	})
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/write")
	public void firstAddReplyBoard(@RequestBody ReplyBoardDto replyBoardDto) {
		log.debug("first add ReplyBoard : {}", replyBoardDto);
		replyBoardService.addReplyBoard(replyBoardDto);

	}

	@ApiOperation(value = "Q&A 답글 등록", notes = "<big><strong>Q&A의 답글</strong></big>을 등록.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "Q&A 답글 등록 정상 처리") 
	})
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/write/{articleno}")
	public void addReplyBoard(@RequestBody Map<String, ReplyBoardDto> map) {
		replyBoardService.addReplyBoard(map);
		log.debug("map : {}", map.get("origin"));

	}

	@ApiOperation(value = "Q&A 수정", notes = "<big><strong>Q&A(답글 포함)</strong></big>을 수정.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "Q&A 수정 정상 처리") 
	})
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PutMapping("/write/{articleno}")
	public void modifyReplyBoard(@RequestBody ReplyBoardDto replyBoardDto) {
		log.debug("Modify ReplyBoard : {}", replyBoardDto);
		replyBoardService.modifyReplyBoard(replyBoardDto);
	}

	@ApiOperation(value = "Q&A 삭제", notes = "<big><strong>Q&A(원본 Q&A이면 답글 포함 다 삭제 | 답글이면 답글만 삭제)</strong></big> 삭제.")
	@ApiResponses({ 
		@ApiResponse(code = 405, message = "지원하지 않는 메서드 호출!!!"),
		@ApiResponse(code = 500, message = "서버에러!!!"),	
		@ApiResponse(code = 200, message = "Q&A 삭제 정상 처리") 
	})
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@DeleteMapping("/write/{articleno}/{ref}")
	public void deleteReplyBoard(@PathVariable Integer articleno,@PathVariable Integer ref) {
		log.debug("Delete ReplyBoard! : {} , {}", articleno, ref);
		replyBoardService.deleteReplyBoard(articleno, ref);
	}

}
