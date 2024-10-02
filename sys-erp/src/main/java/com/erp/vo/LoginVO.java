package com.erp.vo;

import lombok.Data;

@Data
public class LoginVO {
	private String empId;
    private String empPassword;
    private boolean remeberMe;
}
