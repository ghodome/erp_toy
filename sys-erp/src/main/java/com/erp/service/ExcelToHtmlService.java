package com.erp.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ExcelToHtmlService {

    public String convertExcelToHtml(String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(filePath)) {
            Sheet sheet = workbook.getSheetAt(0);  // 첫 번째 시트 가져오기
            StringBuilder htmlBuilder = new StringBuilder();

            htmlBuilder.append("<table border='1'>");

            // 행과 열을 반복하여 HTML 테이블 생성
            for (Row row : sheet) {
                htmlBuilder.append("<tr>");
                for (Cell cell : row) {
                    htmlBuilder.append("<td>").append(getCellValue(cell)).append("</td>");
                }
                htmlBuilder.append("</tr>");
            }

            htmlBuilder.append("</table>");
            return htmlBuilder.toString();
        }
    }

    // 셀의 값을 가져오는 메서드
    private String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
