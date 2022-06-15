package com.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.happyhouse.model.ReplyBoardDto;

public interface ReplyBoardService {

	List<ReplyBoardDto> replyBoardList();
	
	ReplyBoardDto getReplyBoard(Integer articleno);
	
	void addReplyBoard(ReplyBoardDto replyBoardDto);
	
	void addReplyBoard(Map<String, ReplyBoardDto> map);
	
	void modifyReplyBoard(ReplyBoardDto replyBoardDto);
	
	void deleteReplyBoard(Integer articleno, Integer ref);
	
}
