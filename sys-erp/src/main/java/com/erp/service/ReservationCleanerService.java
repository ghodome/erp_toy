package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.erp.dao.MeetingDao;

@Service
public class ReservationCleanerService {

    @Autowired
    private MeetingDao meetingDao;

    //월-금 8시부터 18시까지 30분간격으로 실행
    @Scheduled(cron = "0 0/30 8-18 * * 1-5")
    public void cleanExpiredMeetings() {
        meetingDao.deleteExpiredMeetings(); 
        System.out.println("청소 완료");
    }
}
