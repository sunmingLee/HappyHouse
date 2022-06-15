package com.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.ReplyBoardDto;

@Mapper
public interface ReplyBoardMapper {
	List<ReplyBoardDto> replyBoardList();
	
	ReplyBoardDto getReplyBoard(Integer articleno);
	
	void addFirstReplyBoard(ReplyBoardDto replyBoardDto);
	
	void addReplyBoard(Map<String, ReplyBoardDto> map);
	
	void modifyReplyBoard(ReplyBoardDto replyBoardDto);
	
	void deleteReplyBoard(Integer articleno);
	
	void deleteReplyBoardCascade(Integer articleno);
	
	void modifyReplyBoardRef(ReplyBoardDto replyBoardDto);
}
