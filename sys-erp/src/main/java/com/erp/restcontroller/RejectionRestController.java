package com.erp.restcontroller;

import com.erp.dto.RejectionDto;
import com.erp.service.RejectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})//CORS 해제 설정
@RestController
@RequestMapping("/api/rejections")
public class RejectionRestController {

    @Autowired
    private RejectionService rejectionService;

    @PostMapping
    public ResponseEntity<Void> createRejection(@RequestBody RejectionDto rejectionDto) {
        rejectionService.saveRejection(rejectionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/document/{documentNo}")
    public ResponseEntity<List<RejectionDto>> getRejectionsByDocument(@PathVariable int documentNo) {
        List<RejectionDto> rejections = rejectionService.getRejectionsByDocument(documentNo);
        return ResponseEntity.ok(rejections);
    }
}
