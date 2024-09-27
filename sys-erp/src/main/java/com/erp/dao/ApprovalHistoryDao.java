package com.erp.dao;

import com.erp.dto.ApprovalHistoryDto;
import com.erp.mapper.ApprovalHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApprovalHistoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ApprovalHistoryMapper approvalHistoryMapper;

    // 결재 이력 저장
    public void insert(ApprovalHistoryDto approvalHistoryDto) {
        String sql = "INSERT INTO approval_history (approval_no, approval_emp, approval_previous_status, approval_new_status, approval_change_reason, approval_changedAt) " +
                     "VALUES (?, ?, ?, ?, ?, SYSDATE)";
        Object[] data = {
            approvalHistoryDto.getApprovalNo(),
            approvalHistoryDto.getApprovalEmp(),
            approvalHistoryDto.getApprovalPreviousStatus(),
            approvalHistoryDto.getApprovalNewStatus(),
            approvalHistoryDto.getApprovalChangeReason()
        };
        jdbcTemplate.update(sql, data);
    }

    // 특정 결재번호로 결재 이력 조회
    public List<ApprovalHistoryDto> selectByApprovalNo(int approvalNo) {
        String sql = "SELECT * FROM approval_history WHERE approval_no = ?";
        Object[] data = {approvalNo};
        return jdbcTemplate.query(sql, approvalHistoryMapper, data);
    }

    // 모든 결재 이력 조회
    public List<ApprovalHistoryDto> selectAll() {
        String sql = "SELECT * FROM approval_history";
        return jdbcTemplate.query(sql, approvalHistoryMapper);
    }
}
