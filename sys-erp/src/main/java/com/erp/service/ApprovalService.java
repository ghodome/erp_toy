package com.erp.service;

import com.erp.dao.ApprovalDao;
import com.erp.dao.DocumentDao;
import com.erp.dto.ApprovalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalDao approvalDao;

    @Autowired
    private DocumentDao documentDao;

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
        // 결재 상태 업데이트
        approvalDao.updateApprovalStatus(approvalNo, status);

        // 해당 결재에 연관된 문서 번호 가져오기
        int documentNo = approvalDao.findDocumentNoByApprovalNo(approvalNo);

        // 문서 상태 업데이트
        String documentStatus;
        if (status.equals("승인완료")) {
            documentStatus = "승인완료";
        } else if (status.equals("반려됨")) {
            documentStatus = "반려됨";
        } else {
            documentStatus = "진행중";
        }
        documentDao.updateDocumentStatus(documentNo, documentStatus);

        // 문서 상태 변경 시간 기록
        documentDao.updateDocumentUpdateAt(documentNo, new Date());
    }

    @Transactional
    public void deleteApproval(int approvalNo) {
        approvalDao.deleteApproval(approvalNo);
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