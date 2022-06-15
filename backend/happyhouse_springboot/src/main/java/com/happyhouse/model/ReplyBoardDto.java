package com.happyhouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyBoardDto {
	private Integer articleno;
	private String subject;
	private String content;
	private Integer ref;
	private Integer step;
	private Integer depth;
	private String author;
	private String user_id;
	private String regdate;

}
