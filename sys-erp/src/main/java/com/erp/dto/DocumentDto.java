package com.erp.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DocumentDto {

	
	private int documentNo;
	private String documentTitle;
	private String documentContent;
	private String documentStatus;
	private String documentCreateBy;
	private Date documentCreateAt;
	private Date documentUpdateAt;
	private int category_code;
}
