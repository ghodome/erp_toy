
package com.erp.service;

import com.erp.dao.ApprovalDao;
import com.erp.dto.ApprovalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalDao approvalDao;

    @Transactional
    public void saveApproval(ApprovalDto approvalDto) {
        approvalDao.insert(approvalDto);
    }

    @Transactional(readOnly = true)
    public List<ApprovalDto> getApprovalsByDocument(int documentNo) {
        return approvalDao.selectByDocument(documentNo);
    }

    @Transactional
    public void updateApprovalStatus(int approvalNo, String status) {
        approvalDao.updateApprovalStatus(approvalNo, status);
    }

    @Transactional
    public void deleteApproval(int approvalNo) {
        approvalDao.deleteApproval(approvalNo);
    }

    @Transactional(readOnly = true)
    public List<ApprovalDto> selectByApprover(String empId) {
        return approvalDao.selectByApprover(empId);
    }

    @Transactional(readOnly = true)
    public List<ApprovalDto> getApprovalsByStatus(String status) {
        return approvalDao.selectByStatus(status);
    }

    @Transactional(readOnly = true)
    public int countAll() {
        return approvalDao.countAll();
    }

    @Transactional(readOnly = true)
    public int countPendingApprovals(String empId) {
        return approvalDao.countPendingApprovals(empId);
    }

    @Transactional(readOnly = true)
    public List<ApprovalDto> selectApprovalHistory(int documentNo) {
        return approvalDao.selectApprovalHistory(documentNo);
    }
}
