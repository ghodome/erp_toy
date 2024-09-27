package com.erp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.erp.dao.DocumentDao;
import com.erp.dto.DocumentDto;

@Service
public class DocumentService {

    @Autowired
    private DocumentDao documentDao;

    public void addDocument(DocumentDto documentDto) {
        documentDao.insertDocument(documentDto);
    }

    public void modifyDocument(DocumentDto documentDto) {
        documentDao.updateDocument(documentDto);
    }

    public DocumentDto findDocumentByNo(int documentNo) {
        return documentDao.getDocumentByNo(documentNo);
    }

    public List<DocumentDto> findAllDocuments() {
        return documentDao.getAllDocuments();
    }
    
    public List<DocumentDto> findDocumentsByStatus(String documentStatus) {
        return documentDao.getDocumentsByStatus(documentStatus);
    }
}
