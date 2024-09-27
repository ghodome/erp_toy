package com.erp.dto;

import lombok.Data;

@Data
public class ChatroomDto {
	private int chatroomNo;
	private String chatroomName;
	private String chatroomLastMsg;
	private String chatroomLeave;
}
