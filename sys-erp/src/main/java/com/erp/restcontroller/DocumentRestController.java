package com.erp.restcontroller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Date;
import org.springframework.http.HttpStatus;


import com.erp.dto.DocumentDto;
import com.erp.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000"})//CORS 해제 설정
@RestController
@RequestMapping("/api/documents")
public class DocumentRestController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<List<DocumentDto>> getDocumentsByCategory(@PathVariable int categoryCode) {
        List<DocumentDto> documents = documentService.findByCategory(categoryCode);
        return ResponseEntity.ok(documents);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createDocument(@RequestBody DocumentDto documentDto) {
    	System.out.println("documentDto="+documentDto);
        try {
            // 현재 인증된 사용자 ID 설정
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName(); // 사용자 ID 가져오기
            documentDto.setDocumentCreateBy(username);

            // 현재 시간 설정
            documentDto.setDocumentCreateAt(new Date());

            // 문서 상태 기본값 설정
            if (documentDto.getDocumentStatus() == null) {
                documentDto.setDocumentStatus("진행중");
            }

            // 카테고리 코드 기본값 설정
            if (documentDto.getCategoryCode() == 0) {
                documentDto.setCategoryCode(1); // 적절한 기본값으로 설정
            }

            documentService.saveDocument(documentDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{documentNo}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable int documentNo) {
        DocumentDto document = documentService.findById(documentNo);
        return ResponseEntity.ok(document);
    }

    @PutMapping("/{documentNo}")
    public ResponseEntity<Void> updateDocument(@PathVariable int documentNo, @RequestBody DocumentDto documentDto) {
        documentDto.setDocumentNo(documentNo);
        boolean updated = documentService.updateDocument(documentDto);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{documentNo}")
    public ResponseEntity<Void> deleteDocument(@PathVariable int documentNo) {
        boolean deleted = documentService.deleteDocument(documentNo);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
