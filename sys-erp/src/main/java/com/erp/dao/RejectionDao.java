package com.erp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.erp.dto.RejectionDto;
import com.erp.mapper.RejectionMapper;

import java.util.List;

@Repository
public class RejectionDao {

    @Autowired 
    private JdbcTemplate jdbcTemplate;

    @Autowired 
    private RejectionMapper rejectionMapper;

    // 반려 저장
    public void insert(RejectionDto rejectionDto) {
        String sql = "INSERT INTO rejection (rejection_document, rejection_emp, rejection_reason, rejection_createAt) VALUES (?, ?, ?, SYSDATE)";
        Object[] data = {
            rejectionDto.getRejectionDocument(),
            rejectionDto.getRejectionEmp(),
            rejectionDto.getRejectionReason()
        };
        jdbcTemplate.update(sql, data);
    }

    // 특정 문서에 대한 반려 내역 조회
    public List<RejectionDto> selectByDocument(int documentNo) {
        String sql = "SELECT * FROM rejection WHERE rejection_document = ?";
        Object[] data = { documentNo }; 
        return jdbcTemplate.query(sql, rejectionMapper, data);
    }

    // 특정 사원이 반려한 문서 조회
    public List<RejectionDto> selectByEmployee(String empId) {
        String sql = "SELECT * FROM rejection WHERE rejection_emp = ?";
        Object[] data = { empId }; 
        return jdbcTemplate.query(sql, rejectionMapper, data);
    }

    // 반려 내역 전체 조회
    public List<RejectionDto> selectAll() {
        String sql = "SELECT * FROM rejection";
        return jdbcTemplate.query(sql, rejectionMapper); // 데이터가 필요없으므로 Object[] 제거
    }

    // 반려 사유 업데이트
    public void updateRejectionReason(int rejectionNo, String reason) {
        String sql = "UPDATE rejection SET rejection_reason = ? WHERE rejection_no = ?";
        Object[] data = {reason, rejectionNo};
        jdbcTemplate.update(sql, data);
    }

    // 반려 사유 통계
    public List<String> getRejectionReasonsStatistics() {
        String sql = "SELECT rejection_reason, COUNT(*) FROM rejection GROUP BY rejection_reason";
        return jdbcTemplate.queryForList(sql, String.class); // Object[] 제거
    }

    // 반려된 문서 수 조회
    public int countRejectedDocuments(String empId) {
        String sql = "SELECT COUNT(*) FROM rejection WHERE rejection_emp = ?";
        Object[] data = {empId}; 
        return jdbcTemplate.queryForObject(sql, Integer.class, data);
    }
}
