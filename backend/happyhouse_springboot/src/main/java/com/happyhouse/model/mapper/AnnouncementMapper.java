package com.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.AnnouncementDto;

@Mapper
public interface AnnouncementMapper {
	List<AnnouncementDto> findAnnouncement();

	AnnouncementDto announcementInfo(int announcementNum);

	void writeAnnouncement(AnnouncementDto announcementDto);

	void modifyAnnouncement(AnnouncementDto announcementDto);

	void deleteAnnouncement(int announcementNum);

}
