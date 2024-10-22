package com.erp.restcontroller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dto.DocumentDto;
import com.erp.dto.EmpDto;
import com.erp.service.ApprovalService;
import com.erp.service.DocumentService;
import com.erp.service.EmpService;
import com.erp.service.SignatureService;

@CrossOrigin(origins = {"http://localhost:3000"}) // CORS 설정
@RestController
@RequestMapping("/api/documents")
public class DocumentRestController {

    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private SignatureService signatureService;

    @Autowired
    private ApprovalService approvalService;
    
    @Autowired
    private EmpService empService;

    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<List<DocumentDto>> getDocumentsByCategory(@PathVariable int categoryCode) {
        List<DocumentDto> documents = documentService.findByCategory(categoryCode);
        return ResponseEntity.ok(documents);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createDocument(@RequestBody DocumentDto documentDto) {
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

    @PutMapping("/{documentNo}/status")
    public ResponseEntity<Void> updateDocumentStatus(@PathVariable int documentNo, @RequestBody Map<String, String> statusUpdate) {
        String newStatus = statusUpdate.get("status");
        DocumentDto documentDto = documentService.findById(documentNo);
        if (documentDto != null) {
            documentDto.setDocumentStatus(newStatus);
            documentDto.setDocumentUpdateAt(new Date());  // 업데이트 날짜 설정
            boolean updated = documentService.updateDocument(documentDto);
            return updated ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{documentNo}")
    public ResponseEntity<Void> deleteDocument(@PathVariable int documentNo) {
        boolean deleted = documentService.deleteDocument(documentNo);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<DocumentDto>> getAllDocuments() {
        List<DocumentDto> documents = documentService.findAll();
        return ResponseEntity.ok(documents); // List 형식으로 반환
    }
 // DocumentRestController.java

 // 예를 들어, 문서 서명을 처리하는 엔드포인트
    @PostMapping("/{documentNo}/sign")
    public ResponseEntity<Void> signDocument(@PathVariable int documentNo, @RequestBody Map<String, String> payload) {
        String empNo = payload.get("empNo");
        String empSignature = payload.get("empSignature");

        try {
            signatureService.saveOrUpdateSignature(empNo, empSignature);
            // 추가적으로 문서 승인/서명 처리 로직 추가
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
}
