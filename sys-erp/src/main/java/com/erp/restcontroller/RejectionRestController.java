package com.erp.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.erp.dto.RejectionDto;
import com.erp.service.RejectionService;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/rejections")
public class RejectionRestController {

    @Autowired
    private RejectionService rejectionService;

    /**
     * 반려 정보 등록
     * @param rejectionDto
     * @return 성공 메시지
     */
    @PostMapping
    public ResponseEntity<String> createRejection(@RequestBody RejectionDto rejectionDto) {
        rejectionService.addRejection(rejectionDto);
        return ResponseEntity.ok("Rejection created successfully");
    }

    /**
     * 특정 문서에 대한 반려 정보 조회
     * @param rejectionDocument 반려 문서 번호
     * @return 반려 정보
     */
    @GetMapping("/document/{rejectionDocument}")
    public ResponseEntity<RejectionDto> getRejectionByDocument(@PathVariable int rejectionDocument) {
        RejectionDto rejection = rejectionService.findRejectionByDocument(rejectionDocument);
        return ResponseEntity.ok(rejection);
    }
}
