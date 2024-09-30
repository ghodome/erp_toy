package com.erp.restcontroller;

import com.erp.service.ExcelToHtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelToHtmlRestController {

    @Autowired
    private ExcelToHtmlService excelToHtmlService;

    @GetMapping("/convert")
    public ResponseEntity<String> convertExcelToHtml() {
        try {
            String filePath = "path_to_excel_file.xlsx"; // 엑셀 파일 경로
            String htmlTable = excelToHtmlService.convertExcelToHtml(filePath);
            return ResponseEntity.ok(htmlTable);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("엑셀 파일을 읽는 중 오류가 발생했습니다.");
        }
    }
}
