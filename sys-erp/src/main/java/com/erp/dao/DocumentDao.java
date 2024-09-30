package com.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.erp.dto.DocumentDto;
@Mapper
public interface DocumentDao {

    void insertDocument(DocumentDto documentDto);

    void updateDocument(DocumentDto documentDto);

    DocumentDto getDocumentByNo(@Param("documentNo") int documentNo);

    List<DocumentDto> getAllDocuments();
    
    List<DocumentDto> getDocumentsByStatus(@Param("documentStatus") String documentStatus);
}
