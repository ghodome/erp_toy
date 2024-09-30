package com.erp.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChatDto {
	private int chatNo;
	private int chatroomNo;
	private String chatId;
	private String chatContent;
	private int chatRead;
	private Date chatTime;
	private String chatDelete;
}
