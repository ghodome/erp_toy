package com.erp.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dto.RejectionDto;
import com.erp.service.ApprovalService;
import com.erp.service.RejectionService; // RejectionService를 통해 비즈니스 로직을 처리합니다.

@RestController
@RequestMapping("/api")
public class RejectionRestController {

    @Autowired
    private RejectionService rejectionService;
    @Autowired
    private ApprovalService approvalService;

    // 반려 저장
    @PostMapping
    public void createRejection(@RequestBody RejectionDto rejectionDto) {
        rejectionService.saveRejection(rejectionDto); 
    }

    // 특정 문서에 대한 반려 내역 조회
    @GetMapping("/document/{documentNo}")
    public List<RejectionDto> getRejectionsByDocument(@PathVariable int documentNo) {
        return rejectionService.getRejectionsByDocument(documentNo);
    }

    // 특정 사원이 반려한 문서 조회
    @GetMapping("/employee/{empId}")
    public List<RejectionDto> getRejectionsByEmployee(@PathVariable String empId) {
        return rejectionService.getRejectionsByEmployee(empId);
    }

    // 반려 내역 전체 조회
    @GetMapping
    public List<RejectionDto> getAllRejections() {
        return rejectionService.selectAll();
    }

    // 반려 사유 업데이트
    @PutMapping("/{rejectionNo}")
    public void updateRejectionReason(@PathVariable int rejectionNo, @RequestParam String reason) {
        rejectionService.updateRejectionReason(rejectionNo, reason);
    }

    // 반려 사유 통계
    @GetMapping("/statistics")
    public List<String> getRejectionReasonsStatistics() {
      
    	return rejectionService.getRejectionReasonsStatistics();
    }

    // 반려된 문서 수 조회
    @GetMapping("/count/employee/{empId}")
    public int countRejectedDocuments(@PathVariable String empId) {
        return rejectionService.countRejectedDocuments(empId);
    }
    
}
