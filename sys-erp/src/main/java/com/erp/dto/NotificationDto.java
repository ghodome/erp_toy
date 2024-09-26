package com.erp.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class NotificationDto {
	
	private int notificationNo;
	private String notificationEmp;
	private int notificationDocument;
	private String notificationType;
	private String notificationMessage;
	private Date notificationCreateAt;
	private String notificationRead;
	
}
