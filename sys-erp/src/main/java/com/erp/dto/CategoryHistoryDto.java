package com.erp.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CategoryHistoryDto {
	private int categoryHistoryNo;
	private int categoryCode;
	private String categoryType;
	private Date categoryHistoryTime;
}
