package com.erp.restcontroller;

import com.erp.dto.NotificationDto;
import com.erp.service.NotificationService; // NotificationService를 통해 비즈니스 로직을 처리합니다.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;

    // 알림 저장
    @PostMapping
    public void createNotification(@RequestBody NotificationDto notificationDto) {
        notificationService.sendNotification(notificationDto); // 수정된 부분
    }

    // 특정 사용자에 대한 알림 조회
    @GetMapping("/employee/{empId}")
    public List<NotificationDto> getNotificationsByEmployee(@PathVariable String empId) {
        return notificationService.getNotificationsByEmployee(empId); // 수정된 부분
    }

    // 읽지 않은 알림 조회
    @GetMapping("/employee/{empId}/unread")
    public List<NotificationDto> getUnreadNotificationsByEmployee(@PathVariable String empId) {
        return notificationService.getUnreadNotificationsByEmployee(empId); // 수정된 부분
    }

    // 특정 알림 유형 조회
    @GetMapping("/type/{notificationType}")
    public List<NotificationDto> getNotificationsByType(@PathVariable String notificationType) {
       // return notificationService.getNotificationsByType(notificationType); // 수정된 부분
    	return notificationService.getNotificationsByType(notificationType);
    }

    // 알림 읽음 처리
    @PutMapping("/{notificationNo}/read")
    public void markNotificationAsRead(@PathVariable int notificationNo) {
        notificationService.markNotificationAsRead(notificationNo); // 수정된 부분
    }

    // 알림 삭제
    @DeleteMapping("/{notificationNo}")
    public void deleteNotification(@PathVariable int notificationNo) {
        notificationService.deleteNotification(notificationNo);
    }

    // 전체 알림 수 조회
    @GetMapping("/count")
    public int countAllNotifications() {
        return notificationService.countAll(); // 추가된 부분
    }

    // 알림 읽음 상태 변경 이력 기록
    @PostMapping("/{notificationNo}/read/history")
    public void logNotificationReadChange(@PathVariable int notificationNo, @RequestParam String empId) {
        notificationService.logNotificationReadChange(notificationNo, empId); // 추가된 부분
    }
}
