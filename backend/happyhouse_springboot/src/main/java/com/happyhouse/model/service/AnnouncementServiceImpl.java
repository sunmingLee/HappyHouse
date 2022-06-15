package com.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.model.AnnouncementDto;
import com.happyhouse.model.mapper.AnnouncementMapper;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementMapper announcementMapper;

	@Override
	public List<AnnouncementDto> announcementList() {
//		int list_cnt = 5;
//		int pageCnt = (pageNum - 1) * list_cnt;
		List<AnnouncementDto> list = announcementMapper.findAnnouncement();
		return list;
	}

	@Override
	public AnnouncementDto AnnouncementInfo(int announcementNum) {
		return announcementMapper.announcementInfo(announcementNum);
	}

	@Override
	public void writeAnnouncement(AnnouncementDto announcementDto) {
		announcementMapper.writeAnnouncement(announcementDto);
	}

	@Override
	public void modifyAnnouncement(AnnouncementDto announcementDto) {
		announcementMapper.modifyAnnouncement(announcementDto);
	}

	@Override
	public void deleteAnnouncement(int announcementNo) {
		announcementMapper.deleteAnnouncement(announcementNo);
	}

}
