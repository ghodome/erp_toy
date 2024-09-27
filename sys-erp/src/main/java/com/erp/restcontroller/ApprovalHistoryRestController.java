package com.erp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.dto.ApprovalHistoryDto;
import com.erp.service.ApprovalHistoryService;

@RestController
@RequestMapping("/api/approvalHistories")
public class ApprovalHistoryRestController {

    @Autowired
    private ApprovalHistoryService approvalHistoryService;

    /**
     * 결재 이력 등록
     * @param approvalHistoryDto
     * @return 성공 메시지
     */
    @PostMapping
    public ResponseEntity<String> createApprovalHistory(@RequestBody ApprovalHistoryDto approvalHistoryDto) {
        approvalHistoryService.addApprovalHistory(approvalHistoryDto);
        return ResponseEntity.ok("Approval history created successfully");
    }

    /**
     * 특정 결재 번호에 해당하는 결재 이력 조회
     * @param approvalNo 결재 번호
     * @return 결재 이력 리스트
     */
    @GetMapping("/{approvalNo}")
    public ResponseEntity<List<ApprovalHistoryDto>> getApprovalHistoryByApprovalNo(@PathVariable int approvalNo) {
        List<ApprovalHistoryDto> historyList = approvalHistoryService.findApprovalHistoryByApprovalNo(approvalNo);
        return ResponseEntity.ok(historyList);
    }
}
