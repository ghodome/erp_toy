package com.erp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PortDto {
	private int portNo;
	private int portProductNo;
	private String portManager;
	private int portStorageNo;
	private int portQty;
	private int portTradingValue;
	private String portCorrespondent;
	private String portUnit;
	private Date portDate;
}
