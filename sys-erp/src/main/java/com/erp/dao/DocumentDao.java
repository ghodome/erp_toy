package com.erp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.erp.dto.DocumentDto;

@Repository
public class DocumentDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(DocumentDto dto) {
        sqlSession.insert("document.save", dto);
    }

    public List<DocumentDto> selectListByCategory(int categoryCode) {
        return sqlSession.selectList("document.findByCategory", categoryCode);
    }

    public DocumentDto selectOne(int documentNo) {
        return sqlSession.selectOne("document.detail", documentNo);
    }

    public boolean update(DocumentDto dto) {
        int result = sqlSession.update("document.edit", dto);
        return result > 0;
    }

    public boolean delete(int documentNo) {
        int result = sqlSession.delete("document.delete", documentNo);
        return result > 0;
    }
}
