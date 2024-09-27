package com.erp.service;

import com.erp.dao.ApprovalHistoryDao;
import com.erp.dto.ApprovalHistoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalHistoryService {

    @Autowired
    private ApprovalHistoryDao approvalHistoryDao;

    // 새로운 결재 이력 추가
    @Transactional
    public void saveApprovalHistory(ApprovalHistoryDto approvalHistoryDto) {
        approvalHistoryDao.insert(approvalHistoryDto);
    }

    // 특정 결재에 대한 이력 조회
    @Transactional(readOnly = true)
    public List<ApprovalHistoryDto> getApprovalHistoryByApprovalNo(int approvalNo) {
        return approvalHistoryDao.selectByApprovalNo(approvalNo);
    }

    // 모든 결재 이력 조회
    @Transactional(readOnly = true)
    public List<ApprovalHistoryDto> getAllApprovalHistory() {
        return approvalHistoryDao.selectAll();
    }
}
