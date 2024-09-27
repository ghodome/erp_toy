package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.ApprovalDao;
import com.erp.dto.ApprovalDto;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalDao approvalDao;

    // 결재 등록
    public void addApproval(ApprovalDto approvalDto) {
        approvalDao.insertApproval(approvalDto);
    }

    // 결재 수정
    public void modifyApproval(ApprovalDto approvalDto) {
        approvalDao.updateApproval(approvalDto);
    }

    // 결재 번호로 결재 조회
    public ApprovalDto findApprovalByNo(int approvalNo) {
        return approvalDao.getApprovalByNo(approvalNo);
    }

    // 결재 목록 조회
    public List<ApprovalDto> findAllApprovals() {
        return approvalDao.getAllApprovals();
    }

    // 특정 사원의 결재 정보 조회
    public List<ApprovalDto> findApprovalsByEmp(String approvalEmp) {
        return approvalDao.getApprovalsByEmp(approvalEmp);
    }

    // 결재선 저장 로직
    public void saveApprovalLine(List<ApprovalDto> approvalLines) {
        for (ApprovalDto approvalLine : approvalLines) {
            approvalDao.insertApproval(approvalLine);
        }
    }
}
