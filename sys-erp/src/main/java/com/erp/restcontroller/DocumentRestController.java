package com.erp.restcontroller;

import com.erp.dto.DocumentDto;

import com.erp.service.DocumentService; // DocumentService를 통해 비즈니스 로직을 처리합니다.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.ByteArrayOutputStream;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentRestController {

    @Autowired
    private DocumentService documentService;

    // 문서 저장
    @PostMapping
    public void createDocument(@RequestBody DocumentDto documentDto) {
      
    	documentService.saveDocument(documentDto);
    }

    // 문서 전체 조회
    @GetMapping
    public List<DocumentDto> getAllDocuments() {
        
    	return documentService.getAllDocuments();
    }

    // 특정 문서 조회
    @GetMapping("/{documentNo}")
    public DocumentDto getDocumentById(@PathVariable int documentNo) {
        return documentService.getDocumentById(documentNo); 
    }

    // 문서 제목으로 조회
    @GetMapping("/title/{title}")
    public List<DocumentDto> getDocumentsByTitle(@PathVariable String title) {
        return documentService.selectByTitle(title); 
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
        return documentService.countAll(); 
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
        documentService.logDocumentUpdate(documentNo, updatedBy); 
    }
    public byte[] generatePdf(int documentNo) {
        // 문서 내용을 가져오는 로직
        DocumentDto documentDto = getDocumentById(documentNo);
        String content = "문서 제목: " + documentDto.getDocumentTitle() + "\n문서 내용: " + documentDto.getDocumentContent();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // PDF 생성
        try {
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);
            document.add(new Paragraph(content));
            document.close();
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }

        return byteArrayOutputStream.toByteArray();
    }
    
}
