package com.erp.service;

import com.erp.dao.DocumentDao;
import com.erp.dto.DocumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentDao documentDao;

    public List<DocumentDto> findByCategory(int categoryCode) {
        return documentDao.selectListByCategory(categoryCode);
    }

    public void saveDocument(DocumentDto documentDto) {
        documentDao.insert(documentDto);
    }

    public DocumentDto findById(int documentNo) {
        return documentDao.selectOne(documentNo);
    }

    public boolean updateDocument(DocumentDto documentDto) {
        return documentDao.update(documentDto);
    }

    public boolean deleteDocument(int documentNo) {
        return documentDao.delete(documentNo);
    }
}
