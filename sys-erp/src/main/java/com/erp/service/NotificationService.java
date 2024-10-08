package com.erp.service;

import com.erp.dao.NotificationDao;
import com.erp.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    public void saveNotification(NotificationDto notificationDto) {
        notificationDao.insert(notificationDto);
    }

    public List<NotificationDto> getNotificationsByEmp(String empId) {
        return notificationDao.selectListByEmp(empId);
    }
}
