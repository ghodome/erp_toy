package com.erp.restcontroller;

import com.erp.dto.RejectionDto;
import com.erp.service.RejectionService; // RejectionService를 통해 비즈니스 로직을 처리합니다.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rejections")
public class RejectionRestController {

    @Autowired
    private RejectionService rejectionService;

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
