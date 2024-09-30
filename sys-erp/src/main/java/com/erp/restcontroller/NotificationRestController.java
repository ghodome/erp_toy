package com.erp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.dto.NotificationDto;
import com.erp.service.NotificationService;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/notifications")
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 알림 등록
     * @param notificationDto
     * @return 성공 메시지
     */
    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody NotificationDto notificationDto) {
        notificationService.addNotification(notificationDto);
        return ResponseEntity.ok("Notification created successfully");
    }

    /**
     * 특정 사원의 알림 목록 조회
     * @param notificationEmp 사원 ID
     * @return 알림 리스트
     */
    @GetMapping("/employee/{notificationEmp}")
    public ResponseEntity<List<NotificationDto>> getNotificationsByEmployee(@PathVariable String notificationEmp) {
        List<NotificationDto> notifications = notificationService.findNotificationsByEmp(notificationEmp);
        return ResponseEntity.ok(notifications);
    }

    /**
     * 알림 읽음 처리
     * @param notificationNo 알림 번호
     * @return 성공 메시지
     */
    @PutMapping("/read/{notificationNo}")
    public ResponseEntity<String> markNotificationAsRead(@PathVariable int notificationNo) {
        notificationService.markNotificationAsRead(notificationNo);
        return ResponseEntity.ok("Notification marked as read");
    }
}
