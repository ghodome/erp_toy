package com.erp.restcontroller;

import com.erp.dto.ApprovalDto;
import com.erp.service.ApprovalService;
import com.erp.service.DocumentService; // 문서 상태 변경 서비스 추가
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/approvals")
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
    @GetMapping("/{documentNo}")
    public List<ApprovalDto> getApprovalsByDocument(@PathVariable int documentNo) {
        return approvalService.getApprovalsByDocument(documentNo);
    }

    // 결재 상태 업데이트
    @PutMapping("/{approvalNo}/status")
    public void updateApprovalStatus(@PathVariable int approvalNo, @RequestParam String status) {
        // 결재 상태 업데이트
        approvalService.updateApprovalStatus(approvalNo, status);

     
    }

    // 결재 삭제
    @DeleteMapping("/{approvalNo}")
    public void deleteApproval(@PathVariable int approvalNo) {
        approvalService.deleteApproval(approvalNo);
    }

    // 특정 결재자의 결재 내역 조회
    @GetMapping("/approver/{empId}")
    public List<ApprovalDto> getApprovalsByApprover(@PathVariable String empId) {
        return approvalService.selectByApprover(empId);
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
    @GetMapping("/history/{documentNo}")
    public List<ApprovalDto> getApprovalHistory(@PathVariable int documentNo) {
        return approvalService.selectApprovalHistory(documentNo);
    }
}
