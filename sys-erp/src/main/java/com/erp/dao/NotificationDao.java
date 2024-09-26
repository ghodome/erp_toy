package com.erp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.erp.dto.NotificationDto;
import com.erp.mapper.NotificationMapper;

import java.util.List;

@Repository
public class NotificationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NotificationMapper notificationMapper;

    // 알림 저장
    public void insert(NotificationDto notificationDto) {
        String sql = "INSERT INTO notification (notification_emp, notification_document, notification_type, notification_message, notification_createAt, notification_read) VALUES (?, ?, ?, ?, SYSDATE, ?)";
        Object[] data = {
            notificationDto.getNotificationEmp(),
            notificationDto.getNotificationDocument(),
            notificationDto.getNotificationType(),
            notificationDto.getNotificationMessage(),
            notificationDto.getNotificationRead()
        };
        jdbcTemplate.update(sql, data);
    }

    // 특정 사용자에 대한 알림 조회
    public List<NotificationDto> selectByEmployee(String empId) {
        String sql = "SELECT * FROM notification WHERE notification_emp = ?";
        Object[] data = { empId };
        return jdbcTemplate.query(sql, notificationMapper, data);
    }

    // 읽지 않은 알림 조회
    public List<NotificationDto> selectUnreadByEmployee(String empId) {
        String sql = "SELECT * FROM notification WHERE notification_emp = ? AND notification_read = 'N'";
        Object[] data = { empId }; 
        return jdbcTemplate.query(sql, notificationMapper, data);
    }

    // 특정 알림 유형 조회
    public List<NotificationDto> selectByType(String notificationType) {
        String sql = "SELECT * FROM notification WHERE notification_type = ?";
        Object[] data = { notificationType }; 
        return jdbcTemplate.query(sql, notificationMapper, data);
    }

    // 알림 읽음 처리
    public void markAsRead(int notificationNo) {
        String sql = "UPDATE notification SET notification_read = 'Y' WHERE notification_no = ?";
        Object[] data = { notificationNo }; 
        jdbcTemplate.update(sql, data);
    }

    // 알림 삭제
    public void deleteNotification(int notificationNo) {
        String sql = "DELETE FROM notification WHERE notification_no = ?";
        Object[] data = { notificationNo }; 
        jdbcTemplate.update(sql, data);
    }

    // 전체 알림 수 조회
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM notification";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 알림 읽음 상태 변경 이력 기록
    public void logNotificationReadChange(int notificationNo, String empId) {
        String sql = "INSERT INTO notification_read_history (notification_no, emp_id, read_at) VALUES (?, ?, SYSDATE)";
        Object[] data = { notificationNo, empId };
        jdbcTemplate.update(sql, data);
    }
}
