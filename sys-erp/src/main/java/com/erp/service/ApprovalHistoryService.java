package com.erp.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.ApprovalHistoryDao;

import com.erp.dto.ApprovalHistoryDto;

@Service
public class ApprovalHistoryService {

    @Autowired
    private ApprovalHistoryDao approvalHistoryDAO;

    public void addApprovalHistory(ApprovalHistoryDto approvalHistoryDto) {
        approvalHistoryDAO.insertApprovalHistory(approvalHistoryDto);
    }

    public List<ApprovalHistoryDto> findApprovalHistoryByApprovalNo(int approvalNo) {
        return approvalHistoryDAO.getApprovalHistoryByApprovalNo(approvalNo);
    }
}
