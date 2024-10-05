package com.erp.error;

//예외 클래스
public class TargetNotFoundException extends RuntimeException{
	public TargetNotFoundException () {}
	
	public TargetNotFoundException (String msg) {
		super(msg);
	}
	
}
