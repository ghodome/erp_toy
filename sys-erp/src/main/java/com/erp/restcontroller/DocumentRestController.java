package com.erp.restcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/documents")
public class DocumentRestController {

    @GetMapping("/list")
    public List<Map<String, String>> getDocuments() {
        List<Map<String, String>> documents = new ArrayList<>();

        // 샘플 데이터 생성
        Map<String, String> doc1 = new HashMap<>();
        doc1.put("번호", "1");
        doc1.put("카테고리", "인사");
        doc1.put("서브카테고리", "계약");
        doc1.put("제목", "근로계약 요청서");

        Map<String, String> doc2 = new HashMap<>();
        doc2.put("번호", "2");
        doc2.put("카테고리", "인사");
        doc2.put("서브카테고리", "계약");
        doc2.put("제목", "퇴직처리 요청서");

        documents.add(doc1);
        documents.add(doc2);

        return documents; // JSON으로 반환
    }
}
