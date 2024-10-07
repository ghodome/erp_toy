package com.erp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RejectionDto {

	private int rejectionNo;
	private int rejectionDocument;
	private String rejectionEmp;
	private String rejectionReason;
	private Date rejectionCreateAt;
	
	
}
