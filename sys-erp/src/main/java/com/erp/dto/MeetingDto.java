package com.erp.dto;

import lombok.Data;

@Data
public class MeetingDto {
	private int reservationNo;
	private String startTime;
	private String finishTime;
	private String editTime;
	private String editStartTime;
	private String editFinishTime;
}
