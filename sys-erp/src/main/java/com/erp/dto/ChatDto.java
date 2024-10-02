package com.erp.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChatDto {
	private int chatNo;
	private int chatroomNo;
	private String chatId;
	private String chatContent;
	private int chatRead;
	private String chatDelete;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date chatTime;
}
