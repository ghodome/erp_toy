package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.erp.dao.MeetingDao;

@Service
public class ReservationCleanerService {

    @Autowired
    private MeetingDao meetingDao;

    // 5분마다 실행되도록 설정 (5분 = 300000 ms)
    @Scheduled(fixedRate = 300000)
    public void cleanExpiredMeetings() {
        meetingDao.deleteExpiredMeetings(); 
        System.out.println("청소 완료");
    }
}
