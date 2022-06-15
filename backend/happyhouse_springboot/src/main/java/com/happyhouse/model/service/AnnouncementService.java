package com.happyhouse.model.service;

import java.util.List;

import com.happyhouse.model.AnnouncementDto;

public interface AnnouncementService {

	List<AnnouncementDto> announcementList();

	AnnouncementDto AnnouncementInfo(int announcementNum);

	void writeAnnouncement(AnnouncementDto announcementDto);

	void modifyAnnouncement(AnnouncementDto announcementDto);

	void deleteAnnouncement(int announcementNo);
}
