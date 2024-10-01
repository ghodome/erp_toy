package com.erp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.erp.dto.NotificationDto;

@Repository
public class NotificationDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(NotificationDto dto) {
        sqlSession.insert("notification.save", dto);
    }

    public List<NotificationDto> selectListByEmp(String empId) {
        return sqlSession.selectList("notification.findByEmp", empId);
    }
}
