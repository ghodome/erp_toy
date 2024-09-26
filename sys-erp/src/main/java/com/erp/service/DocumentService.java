package com.erp.service;

import com.erp.dao.DocumentDao;
import com.erp.dto.DocumentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentDao documentDao;

    @Transactional
    public void saveDocument(DocumentDto documentDto) {
        documentDao.insert(documentDto);
    }

    @Transactional(readOnly = true)
    public List<DocumentDto> getAllDocuments() {
        return documentDao.selectAll();
    }

    @Transactional(readOnly = true)
    public DocumentDto getDocumentById(int documentNo) {
        return documentDao.selectById(documentNo);
    }

    @Transactional(readOnly = true)
    public List<DocumentDto> searchDocuments(String title, String createdBy, String status) {
        return documentDao.searchDocuments(title, createdBy, status);
    }

    @Transactional
    public void updateDocumentStatus(int documentNo, String status) {
        documentDao.updateDocumentStatus(documentNo, status);
    }

    @Transactional
    public void deleteDocument(int documentNo) {
        documentDao.deleteDocument(documentNo);
    }

    @Transactional(readOnly = true)
    public int countAll() {
        return documentDao.countAll();
    }

    @Transactional(readOnly = true)
    public List<DocumentDto> selectByTitle(String title) { // 추가한 메소드
        return documentDao.selectByTitle(title);
    }

    // 문서 수정 이력 관리 메소드 추가
    @Transactional
    public void logDocumentUpdate(int documentNo, String updatedBy) {
        // log update (구현 세부 사항에 따라 다름)
        documentDao.logDocumentUpdate(documentNo, updatedBy); // 이 메소드가 DocumentDao에 정의되어 있어야 합니다.
    }
}
