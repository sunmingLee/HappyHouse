package com.happyhouse.model;

import java.sql.Date;

import lombok.Data;

@Data
public class AnnouncementDto {
	private int announcementNo;
	private String userid;
	private String subject;
	private String content;
	private Date regtime;
	
	public AnnouncementDto(int announcementNo, String userid, String subject, String content, Date regtime) {
		super();
		this.announcementNo = announcementNo;
		this.userid = userid;
		this.subject = subject;
		this.content = content;
		this.regtime = regtime;
	}
	
}
