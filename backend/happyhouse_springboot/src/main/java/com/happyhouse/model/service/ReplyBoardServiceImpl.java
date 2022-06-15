package com.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.model.ReplyBoardDto;
import com.happyhouse.model.mapper.ReplyBoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReplyBoardServiceImpl implements ReplyBoardService {

	@Autowired
	private ReplyBoardMapper replyBoardMapper;

	@Override
	public List<ReplyBoardDto> replyBoardList() {
		return replyBoardMapper.replyBoardList();
	}

	@Override
	public ReplyBoardDto getReplyBoard(Integer articleno) {
		return replyBoardMapper.getReplyBoard(articleno);
	}

	@Override
	public void addReplyBoard(ReplyBoardDto replyBoardDto) {
		//
		replyBoardMapper.addFirstReplyBoard(replyBoardDto);
		log.info("replyBoardDto: {}", replyBoardDto);
		replyBoardMapper.modifyReplyBoardRef(replyBoardDto);
	}

	@Override
	public void addReplyBoard(Map<String, ReplyBoardDto> map) {

		replyBoardMapper.addReplyBoard(map);
	}

	@Override
	public void modifyReplyBoard(ReplyBoardDto replyBoardDto) {
		// TODO Auto-generated method stub
		replyBoardMapper.modifyReplyBoard(replyBoardDto);
	}

	@Override
	public void deleteReplyBoard(Integer articleno, Integer ref) {

		// 해당 글이 원본 글이면 관련된 글 다 삭제
		if (articleno.equals(ref)) {
			log.info("다 삭제");
			replyBoardMapper.deleteReplyBoardCascade(articleno);
		} else {
			replyBoardMapper.deleteReplyBoard(articleno);
		}
	}

}
