package com.erp.restcontroller;

import com.erp.service.PDFGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
public class DocumentRestController {

    @Autowired
    private PDFGenerationService pdfGenerationService;

    @GetMapping("/download/{documentNo}")
    public ResponseEntity<byte[]> downloadDocumentPdf(@PathVariable int documentNo) {
        try {
            // 해당 문서의 내용을 가져오는 로직 필요
            String documentContent = "결재 문서 내용";  // 실제로는 DB에서 가져와야 함
            
            byte[] pdfBytes = pdfGenerationService.generateApprovalPdf(documentContent);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=document.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
