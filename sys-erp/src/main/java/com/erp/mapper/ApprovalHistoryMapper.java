package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.erp.dto.ApprovalHistoryDto;

@Component
public class ApprovalHistoryMapper implements RowMapper<ApprovalHistoryDto> {

    @Override
    public ApprovalHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ApprovalHistoryDto historyDto = new ApprovalHistoryDto();

        historyDto.setApprovalHistoryNo(rs.getInt("approval_history_no"));
        historyDto.setApprovalNo(rs.getInt("approval_no"));
        historyDto.setApprovalEmp(rs.getString("approval_emp"));
        historyDto.setApprovalPreviousStatus(rs.getString("approval_previous_status"));
        historyDto.setApprovalNewStatus(rs.getString("approval_new_status"));
        historyDto.setApprovalChangeReason(rs.getString("approval_change_reason"));
        historyDto.setApprovalChangedAt(rs.getDate("approval_changedAt"));

        return historyDto;
    }
}

