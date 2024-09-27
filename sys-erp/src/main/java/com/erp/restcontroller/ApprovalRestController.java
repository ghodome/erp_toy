package com.erp.restcontroller;

import com.erp.dto.ApprovalDto;
import com.erp.service.ApprovalService;
import com.erp.service.DocumentService; // 문서 상태 변경 서비스 추가
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")

public class ApprovalRestController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private DocumentService documentService; // 문서 상태 변경 서비스 추가

    // 결재 저장
    @PostMapping
    public void createApproval(@RequestBody ApprovalDto approvalDto) {
        approvalService.saveApproval(approvalDto);
    }

    // 특정 문서에 대한 결재 조회
    @GetMapping("/document/{documentNo}/approvals")
    public ResponseEntity<List<ApprovalDto>> getApprovalsByDocument(@PathVariable int documentNo) {
        List<ApprovalDto> approvals = approvalService.getApprovalsByDocument(documentNo);
        return ResponseEntity.ok(approvals);
    }
 // 결재 상태 업데이트 (승인, 반려 등)
    @PutMapping("/approvals/{approvalNo}/status")
    public ResponseEntity<?> updateApprovalStatus(
        @PathVariable int approvalNo, 
        @RequestParam String status, 
        @RequestParam(required = false) String comment) {
        
        // ApprovalService로 결재 상태 업데이트 요청 (comment 추가)
       
        
        return ResponseEntity.ok("결재 상태가 업데이트되었습니다.");
    }

     
    
 // 결재 삭제
    @DeleteMapping("/approvals/{approvalNo}")
    public ResponseEntity<?> deleteApproval(@PathVariable int approvalNo) {
        approvalService.deleteApproval(approvalNo);
        return ResponseEntity.ok("결재가 삭제되었습니다.");
    }

   
    // 결재 상태별 조회
    @GetMapping("/status/{status}")
    public List<ApprovalDto> getApprovalsByStatus(@PathVariable String status) {
        return approvalService.getApprovalsByStatus(status);
    }

    // 전체 결재 수 조회
    @GetMapping("/count")
    public int countAllApprovals() {
        return approvalService.countAll();
    }

    // 결재 대기 중인 문서 수 조회
    @GetMapping("/pending/{empId}/count")
    public int countPendingApprovals(@PathVariable String empId) {
        return approvalService.countPendingApprovals(empId);
    }
 // 결재 이력 조회
    @GetMapping("/approvals/{documentNo}/history")
    public ResponseEntity<List<ApprovalDto>> getApprovalHistory(@PathVariable int documentNo) {
        return ResponseEntity.ok(approvalService.selectApprovalHistory(documentNo));
    }
    
}
