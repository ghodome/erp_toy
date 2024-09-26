package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.NotificationDto;

@Service
public class NotificationMapper implements RowMapper<NotificationDto> {

	@Override
	public NotificationDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		NotificationDto notificationDto = new NotificationDto();
		
		notificationDto.setNotificationNo(rs.getInt("notification_no"));
		notificationDto.setNotificationEmp(rs.getString("notification_emp"));
		notificationDto.setNotificationDocument(rs.getInt("notification_document"));
		notificationDto.setNotificationType(rs.getString("notification_type"));
		notificationDto.setNotificationMessage(rs.getString("notification_message"));
		notificationDto.setNotificationCreateAt(rs.getDate("notification_createAt"));
		notificationDto.setNotificationRead(rs.getString("notification_read"));
	
		
		return notificationDto;
	}

}
