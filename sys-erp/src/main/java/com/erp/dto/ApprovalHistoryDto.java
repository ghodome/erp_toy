package com.erp.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ApprovalHistoryDto {

	private int approvalHistoryNo;
	private int approvalNo;
	private String approvalEmp;
	private String approvalPreviousStatus;
	private String approvalNewStatus;
	private String approvalChangeReason;
	private Date approvalChangedAt;
}
