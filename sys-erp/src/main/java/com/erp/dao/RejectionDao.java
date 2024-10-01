package com.erp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.erp.dto.RejectionDto;

@Repository
public class RejectionDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(RejectionDto dto) {
        sqlSession.insert("rejection.save", dto);
    }

    public List<RejectionDto> selectListByDocument(int documentNo) {
        return sqlSession.selectList("rejection.findByDocument", documentNo);
    }
}
