package com.erp.restcontroller;

import com.erp.dto.ApprovalDto;
import com.erp.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})//CORS 해제 설정
@RestController
@RequestMapping("/api/approvals")
public class ApprovalRestController {

    @Autowired
    private ApprovalService approvalService;

    @PostMapping
    public ResponseEntity<Void> createApproval(@RequestBody ApprovalDto approvalDto) {
        approvalService.saveApproval(approvalDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/document/{documentNo}")
    public ResponseEntity<List<ApprovalDto>> getApprovalsByDocument(@PathVariable int documentNo) {
        List<ApprovalDto> approvals = approvalService.getApprovalsByDocument(documentNo);
        return ResponseEntity.ok(approvals);
    }
}
