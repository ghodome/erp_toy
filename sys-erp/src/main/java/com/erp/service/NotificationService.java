package com.erp.service;

import com.erp.dao.NotificationDao;
import com.erp.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Transactional
    public void sendNotification(NotificationDto notificationDto) {
        notificationDao.insert(notificationDto);
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> getNotificationsByEmployee(String empId) {
        return notificationDao.selectByEmployee(empId);
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> getUnreadNotificationsByEmployee(String empId) { // 추가된 부분
        return notificationDao.selectUnreadByEmployee(empId); // DAO 메소드에 해당하는 내용
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> getNotificationsByType(String notificationType) { // 추가된 부분
        return notificationDao.selectByType(notificationType); // DAO 메소드에 해당하는 내용
    }

    @Transactional
    public void markNotificationAsRead(int notificationNo) {
        notificationDao.markAsRead(notificationNo);
    }

    @Transactional
    public void deleteNotification(int notificationNo) {
        notificationDao.deleteNotification(notificationNo);
    }

    @Transactional(readOnly = true)
    public int countAll() { // 추가된 부분
        return notificationDao.countAll(); // DAO 메소드에 해당하는 내용
    }

    @Transactional
    public void logNotificationReadChange(int notificationNo, String empId) { // 추가된 부분
        // log the read change (구현 세부 사항에 따라 다름)
        notificationDao.logNotificationReadChange(notificationNo, empId); // DAO 메소드에 해당하는 내용
    }
}
