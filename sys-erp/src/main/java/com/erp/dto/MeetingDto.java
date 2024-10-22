package com.erp.dto;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class MeetingDto {
    private int reservationNo;
    private String empId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime startTime = LocalDateTime.now(); 
    private Date startTime;
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime finishTime = LocalDateTime.now();
    private Date finishTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime editTime = LocalDateTime.now();
    private Date editTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime editStartTime = LocalDateTime.now(); 
    private Date editStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime editFinishTime = LocalDateTime.now(); 
    private Date editFinishTime;
    private String roomName;
    private String purpose;
}

