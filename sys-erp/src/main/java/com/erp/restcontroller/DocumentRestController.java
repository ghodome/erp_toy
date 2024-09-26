package com.erp.restcontroller;

import com.erp.dto.DocumentDto;
import com.erp.service.DocumentService; // DocumentService를 통해 비즈니스 로직을 처리합니다.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentRestController {

    @Autowired
    private DocumentService documentService;

    // 문서 저장
    @PostMapping
    public void createDocument(@RequestBody DocumentDto documentDto) {
        //documentService.saveDocument(documentDto); // 수정된 부분
    	documentService.saveDocument(documentDto);
    }

    // 문서 전체 조회
    @GetMapping
    public List<DocumentDto> getAllDocuments() {
        //return documentService.getAllDocuments(); // 수정된 부분
    	return documentService.getAllDocuments();
    }

    // 특정 문서 조회
    @GetMapping("/{documentNo}")
    public DocumentDto getDocumentById(@PathVariable int documentNo) {
        return documentService.getDocumentById(documentNo); // 수정된 부분
    }

    // 문서 제목으로 조회
    @GetMapping("/title/{title}")
    public List<DocumentDto> getDocumentsByTitle(@PathVariable String title) {
        return documentService.selectByTitle(title); // 수정된 부분
    }

    // 문서 상태 업데이트
    @PutMapping("/{documentNo}/status")
    public void updateDocumentStatus(@PathVariable int documentNo, @RequestParam String status) {
        documentService.updateDocumentStatus(documentNo, status);
    }

    // 문서 삭제
    @DeleteMapping("/{documentNo}")
    public void deleteDocument(@PathVariable int documentNo) {
        documentService.deleteDocument(documentNo);
    }

    // 전체 문서 수 조회
    @GetMapping("/count")
    public int countAllDocuments() {
        return documentService.countAll(); // 수정된 부분
    }

    // 문서 검색 기능 강화
    @GetMapping("/search")
    public List<DocumentDto> searchDocuments(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String createdBy,
            @RequestParam(required = false, defaultValue = "") String status) {
        return documentService.searchDocuments(title, createdBy, status);
    }

    // 문서 수정 이력 관리
    @PostMapping("/{documentNo}/history")
    public void logDocumentUpdate(@PathVariable int documentNo, @RequestParam String updatedBy) {
        documentService.logDocumentUpdate(documentNo, updatedBy); // 이 메소드도 DocumentService에 정의되어 있어야 합니다.
    }
}
