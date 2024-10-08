package com.erp.restcontroller;

import com.erp.dto.NotificationDto;
import com.erp.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})//CORS 해제 설정
@RestController
@RequestMapping("/api/notifications")
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> createNotification(@RequestBody NotificationDto notificationDto) {
        notificationService.saveNotification(notificationDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/emp/{empId}")
    public ResponseEntity<List<NotificationDto>> getNotificationsByEmp(@PathVariable String empId) {
        List<NotificationDto> notifications = notificationService.getNotificationsByEmp(empId);
        return ResponseEntity.ok(notifications);
    }
}
