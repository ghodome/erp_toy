package com.erp.restcontroller;

import com.erp.dto.DocumentDto;
import com.erp.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Void> createDocument(@RequestBody DocumentDto documentDto) {
        documentService.saveDocument(documentDto);
        return ResponseEntity.ok().build();
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
