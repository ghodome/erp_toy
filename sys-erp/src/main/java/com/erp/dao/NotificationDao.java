package com.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.erp.dto.NotificationDto;

@Mapper
public interface NotificationDao {

    void insertNotification(NotificationDto notificationDto);

    List<NotificationDto> getNotificationsByEmp(@Param("notificationEmp") String notificationEmp);

    void updateNotificationAsRead(@Param("notificationNo") int notificationNo);
}
