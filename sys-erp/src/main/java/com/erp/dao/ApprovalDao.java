package com.erp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.erp.dto.ApprovalDto;

@Repository
public class ApprovalDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(ApprovalDto dto) {
        sqlSession.insert("approval.save", dto);
    }

    public List<ApprovalDto> selectListByDocument(int documentNo) {
        return sqlSession.selectList("approval.findByDocument", documentNo);
    }
}
