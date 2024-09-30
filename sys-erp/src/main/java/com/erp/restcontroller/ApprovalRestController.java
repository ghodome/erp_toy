package com.erp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.dto.ApprovalDto;
import com.erp.service.ApprovalService;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/approvals")
public class ApprovalRestController {

    @Autowired
    private ApprovalService approvalService;

    /**
     * 결재 등록 (단일)
     * @param approvalDto
     * @return 성공 메시지
     */
    @PostMapping
    public ResponseEntity<String> createApproval(@RequestBody ApprovalDto approvalDto) {
        approvalService.addApproval(approvalDto);
        return ResponseEntity.ok("Approval created successfully");
    }

    /**
     * 결재선 등록 (복수)
     * @param approvalLines 결재선 리스트
     * @return 성공 메시지
     */
    @PostMapping("/lines")
    public ResponseEntity<String> createApprovalLine(@RequestBody List<ApprovalDto> approvalLines) {
        approvalService.saveApprovalLine(approvalLines);
        return ResponseEntity.ok("Approval lines created successfully");
    }

    /**
     * 결재 정보 업데이트
     * @param approvalNo 결재 번호
     * @param approvalDto 업데이트할 결재 정보
     * @return 성공 메시지
     */
    @PutMapping("/{approvalNo}")
    public ResponseEntity<String> updateApproval(@PathVariable int approvalNo, @RequestBody ApprovalDto approvalDto) {
        approvalDto.setApprovalNo(approvalNo);
        approvalService.modifyApproval(approvalDto);
        return ResponseEntity.ok("Approval updated successfully");
    }

    /**
     * 결재 번호로 특정 결재 조회
     * @param approvalNo 결재 번호
     * @return 조회된 결재 정보
     */
    @GetMapping("/{approvalNo}")
    public ResponseEntity<ApprovalDto> getApprovalByNo(@PathVariable int approvalNo) {
        ApprovalDto approval = approvalService.findApprovalByNo(approvalNo);
        return ResponseEntity.ok(approval);
    }

    /**
     * 전체 결재 목록 조회
     * @return 모든 결재 정보 리스트
     */
    @GetMapping
    public ResponseEntity<List<ApprovalDto>> getAllApprovals() {
        List<ApprovalDto> approvals = approvalService.findAllApprovals();
        return ResponseEntity.ok(approvals);
    }

    /**
     * 특정 사원의 결재 정보 조회
     * @param approvalEmp 결재 사원 ID
     * @return 해당 사원의 결재 정보 리스트
     */
    @GetMapping("/employee/{approvalEmp}")
    public ResponseEntity<List<ApprovalDto>> getApprovalsByEmployee(@PathVariable String approvalEmp) {
        List<ApprovalDto> approvals = approvalService.findApprovalsByEmp(approvalEmp);
        return ResponseEntity.ok(approvals);
    }
    
    // 결재 상태별 문서 조회 API
    @GetMapping("/status/{approvalStatus}")
    public ResponseEntity<List<ApprovalDto>> getApprovalsByStatus(@PathVariable String approvalStatus) {
        List<ApprovalDto> approvals = approvalService.findApprovalsByStatus(approvalStatus);
        return ResponseEntity.ok(approvals);
    }
    // 결재자에게 보이는 문서만 조회하는 API
    @GetMapping("/visible/{empId}")
    public ResponseEntity<List<ApprovalDto>> getVisibleApprovals(@PathVariable String empId) {
        List<ApprovalDto> approvals = approvalService.findVisibleApprovalsForEmp(empId);
        return ResponseEntity.ok(approvals);
    }
}
