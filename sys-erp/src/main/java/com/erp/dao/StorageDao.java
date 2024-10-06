package com.erp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.dto.StorageDto;

@Repository
public class StorageDao {
    @Autowired
    private SqlSession sqlSession;
    
    public boolean insert(StorageDto storageDto) {
        return sqlSession.insert("storage.create", storageDto) > 0; 
    }

    public List<StorageDto> selectList() {
        return sqlSession.selectList("storage.list"); 
    }

    // 저장소 상세 조회
    public StorageDto selectOne(int storageNo) {
        return sqlSession.selectOne("storage.one", storageNo); 
    }

    public boolean update(StorageDto storageDto) {
        return sqlSession.update("storage.edit", storageDto) > 0; 
    }

    public boolean delete(int storageNo) {
        return sqlSession.delete("storage.delete", storageNo) > 0;
    }
}