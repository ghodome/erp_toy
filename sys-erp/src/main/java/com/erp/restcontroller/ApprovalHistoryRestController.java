package com.erp.restcontroller;

import com.erp.dto.ApprovalHistoryDto;
import com.erp.service.ApprovalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/approval-history")
public class ApprovalHistoryRestController {

    @Autowired
    private ApprovalHistoryService approvalHistoryService;

    // 결재 이력 저장
    @PostMapping
    public void createApprovalHistory(@RequestBody ApprovalHistoryDto approvalHistoryDto) {
        approvalHistoryService.saveApprovalHistory(approvalHistoryDto);
    }

    // 특정 결재에 대한 결재 이력 조회
    @GetMapping("/{approvalNo}")
    public List<ApprovalHistoryDto> getApprovalHistoryByApprovalNo(@PathVariable int approvalNo) {
        return approvalHistoryService.getApprovalHistoryByApprovalNo(approvalNo);
    }

    // 모든 결재 이력 조회
    @GetMapping
    public List<ApprovalHistoryDto> getAllApprovalHistory() {
        return approvalHistoryService.getAllApprovalHistory();
    }
}
