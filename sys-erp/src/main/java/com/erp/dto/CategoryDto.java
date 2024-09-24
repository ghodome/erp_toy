package com.erp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoryDto {
	private int categoryCode;
	private String categoryName;
	private String categoryNote;
	private String categoryEnable;
	private int categoryGroup;
	private int categoryOrigin;
	private int categoryDepth;
}
