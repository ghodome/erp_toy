package com.erp.service;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PDFGenerationService {

    public byte[] generateApprovalPdf(String[][] documentData) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // 제목 추가
        document.add(new Paragraph("결재 문서 목록").setBold().setFontSize(18));

        // 표 생성 (열 개수는 문서 데이터에 맞게 설정)
        float[] columnWidths = {1, 3, 3, 6};  // 열 너비 설정
        Table table = new Table(columnWidths);

        // 헤더 추가
        table.addCell(createHeaderCell("번호"));
        table.addCell(createHeaderCell("카테고리"));
        table.addCell(createHeaderCell("서브카테고리"));
        table.addCell(createHeaderCell("제목"));

        // 문서 내용 추가 (전달받은 데이터 배열)
        for (int i = 0; i < documentData.length; i++) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(i + 1))));
            table.addCell(new Cell().add(new Paragraph(documentData[i][0])));  // 카테고리
            table.addCell(new Cell().add(new Paragraph(documentData[i][1])));  // 서브카테고리
            table.addCell(new Cell().add(new Paragraph(documentData[i][2])));  // 제목
        }

        // 표 추가
        document.add(table);

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    // 헤더 셀 생성 메서드
    private Cell createHeaderCell(String content) {
        Cell header = new Cell().add(new Paragraph(content));
        header.setBackgroundColor(new DeviceRgb(173, 216, 230));  // 밝은 파란색 배경
        header.setBold();
        return header;
    }
}
