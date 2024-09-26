package com.erp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.erp.dto.DocumentDto;
import com.erp.mapper.DocumentMapper;

import java.util.List;

@Repository
public class DocumentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DocumentMapper documentMapper;

    // 문서 저장
    public void insert(DocumentDto documentDto) {
        String sql = "INSERT INTO document (document_title, document_content, document_createdBy, document_createdAt) VALUES (?, ?, ?, SYSDATE)";
        Object[] data = {
            documentDto.getDocumentTitle(),
            documentDto.getDocumentContent(),
            documentDto.getDocumentCreateBy()
        };
        jdbcTemplate.update(sql, data);
    }

 // 문서 전체 조회
    public List<DocumentDto> selectAll() {
        String sql = "SELECT * FROM document";
        Object[] data = {}; 
        return jdbcTemplate.query(sql, documentMapper, data);
    }


 // 특정 문서 조회
    public DocumentDto selectById(int documentNo) {
        String sql = "SELECT * FROM document WHERE document_no = ?";
        Object[] data = {documentNo}; 
        List<DocumentDto> list = jdbcTemplate.query(sql, documentMapper, data);
        return list.isEmpty() ? null : list.get(0);
    }


 // 문서 제목으로 조회
    public List<DocumentDto> selectByTitle(String title) {
        String sql = "SELECT * FROM document WHERE document_title LIKE ?";
        Object[] data = {"%" + title + "%"}; 
        return jdbcTemplate.query(sql, documentMapper, data);
    }


 // 문서 상태 업데이트
    public void updateDocumentStatus(int documentNo, String status) {
        String sql = "UPDATE document SET document_status = ? WHERE document_no = ?";
        Object[] data = {status, documentNo};
        jdbcTemplate.update(sql, data);
    }

 // 문서 삭제
    public void deleteDocument(int documentNo) {
        String sql = "DELETE FROM document WHERE document_no = ?";
        Object[] data = {documentNo};
        jdbcTemplate.update(sql, data);
    }


 


 // 전체 문서 수 조회
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM document";
        Object[] data = {}; 
        return jdbcTemplate.queryForObject(sql, Integer.class, data);
    }

    
 // 문서 검색 기능 강화
    public List<DocumentDto> searchDocuments(String title, String createdBy, String status) {
        String sql = "SELECT * FROM document WHERE document_title LIKE ? AND document_createBy LIKE ? AND document_status LIKE ?";
        
        
        Object[] data = {"%" + title + "%", "%" + createdBy + "%", "%" + status + "%"};
        
        return jdbcTemplate.query(sql, documentMapper, data);
    }


 // 문서 수정 이력 관리
    public void logDocumentUpdate(int documentNo, String updatedBy) {
        String sql = "INSERT INTO document_history (document_no, updated_by, updated_at) VALUES (?, ?, SYSDATE)";
        Object[] data = {documentNo, updatedBy};
        jdbcTemplate.update(sql, data);
    }

}
