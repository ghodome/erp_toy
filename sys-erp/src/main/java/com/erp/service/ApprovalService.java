package com.erp.service;

import com.erp.dao.ApprovalDao;
import com.erp.dto.ApprovalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalDao approvalDao;

    public void saveApproval(ApprovalDto approvalDto) {
        approvalDao.insert(approvalDto);
    }

    public List<ApprovalDto> getApprovalsByDocument(int documentNo) {
        return approvalDao.selectByDocumentNo(documentNo);
    }
}
