package com.erp.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class EmpDto {
    private String empId;          // emp_id
    private String empPassword;    // emp_password
    private String empNo;          // emp_no
    private String empName;        // emp_name
    private String empLevel;       // emp_level
    private String empDept;        // emp_dept
    private String empGender;      // emp_gender
    private String empHp;          // emp_hp
    private String empEmail;       // emp_email
    private Date empBirth;         // emp_birth
    private String empEdu;           // emp_edu
    private Date empSdate;       // emp_sdate
    private String empMemo;        // emp_memo
    private String empPost;        // emp_post
    private String empAddress1;    // emp_address1
    private String empAddress2;    // emp_address2
    private String empRole;        // emp_role // 역할
    private String empSignature;    // emp_signature --> 전자 서명 data-uri 형식으로 저장할 예정
}
