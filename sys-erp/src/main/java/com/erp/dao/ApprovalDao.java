package com.erp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.erp.dto.ApprovalDto;
@Mapper
public interface ApprovalDao {

    void insertApproval(ApprovalDto approvalDto);

    void updateApproval(ApprovalDto approvalDto);

    ApprovalDto getApprovalByNo(@Param("approvalNo") int approvalNo);

    List<ApprovalDto> getAllApprovals();
    
    List<ApprovalDto> getApprovalsByEmp(@Param("approvalEmp") String approvalEmp);
    
    // 결재 상태별 문서 조회
    List<ApprovalDto> getApprovalsByStatus(@Param("approvalStatus") String approvalStatus);
}
