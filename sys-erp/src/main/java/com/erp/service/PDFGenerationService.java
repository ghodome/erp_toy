package com.erp.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PDFGenerationService {

    public byte[] generateApprovalPdf(String documentContent) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        
        com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);
        Document document = new Document(pdf);
        
        // 문서 내용 추가
        document.add(new Paragraph("결재 문서"));
        document.add(new Paragraph(documentContent));
        
        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}
