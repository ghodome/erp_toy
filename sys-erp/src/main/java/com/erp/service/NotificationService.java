package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.NotificationDao;
import com.erp.dto.NotificationDto;

@Service
public class NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    public void addNotification(NotificationDto notificationDto) {
        notificationDao.insertNotification(notificationDto);
    }

    public List<NotificationDto> findNotificationsByEmp(String notificationEmp) {
        return notificationDao.getNotificationsByEmp(notificationEmp);
    }

    public void markNotificationAsRead(int notificationNo) {
        notificationDao.updateNotificationAsRead(notificationNo);
    }
}
