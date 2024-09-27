package com.erp.dao;

import java.util.List;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.erp.dto.ApprovalHistoryDto;

@Mapper
public interface ApprovalHistoryDao {

    void insertApprovalHistory(ApprovalHistoryDto approvalHistoryDto);

    List<ApprovalHistoryDto> getApprovalHistoryByApprovalNo(@Param("approvalNo") int approvalNo);
}
