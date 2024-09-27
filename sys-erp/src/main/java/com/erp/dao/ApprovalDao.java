package com.erp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.erp.dto.ApprovalDto;
import com.erp.mapper.ApprovalMapper;

import java.util.List;

@Repository
public class ApprovalDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ApprovalMapper approvalMapper;

    // 결재 저장
    public void insert(ApprovalDto approvalDto) {
        String sql = "INSERT INTO approval (approval_document, approval_emp, approval_order, approval_status) VALUES (?, ?, ?, ?)";
        Object[] data = {
            approvalDto.getApprovalDocument(),
            approvalDto.getApprovalEmp(),
            approvalDto.getApprovalOrder(),
            approvalDto.getApprovalStatus()
        };
        jdbcTemplate.update(sql, data);
    }

    // 결재 상태 업데이트
    public void updateApprovalStatus(int approvalNo, String status) {
        String sql = "UPDATE approval SET approval_status = ? WHERE approval_no = ?";
        Object[] data = {status, approvalNo};
        jdbcTemplate.update(sql, data);
    }

    // 특정 문서에 대한 결재 내역 조회
    public List<ApprovalDto> selectByDocument(int documentNo) {
        String sql = "SELECT * FROM approval WHERE approval_document = ?";
        Object[] data = {documentNo};
        return jdbcTemplate.query(sql, approvalMapper, data);
    }

    // 특정 결재자에 대한 결재 내역 조회
    public List<ApprovalDto> selectByApprover(String empId) {
        String sql = "SELECT * FROM approval WHERE approval_emp = ?";
        Object[] data = {empId};
        return jdbcTemplate.query(sql, approvalMapper, data);
    }

    // 결재 상태별 조회
    public List<ApprovalDto> selectByStatus(String status) {
        String sql = "SELECT * FROM approval WHERE approval_status = ?";
        Object[] data = {status};
        return jdbcTemplate.query(sql, approvalMapper, data);
    }

    // 전체 결재 수 조회
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM approval";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // 결재 대기 중인 문서 수 조회
    public int countPendingApprovals(String empId) {
        String sql = "SELECT COUNT(*) FROM approval WHERE approval_emp = ? AND approval_status = '대기중'";
        Object[] data = {empId};
        return jdbcTemplate.queryForObject(sql, Integer.class, data);
    }

    // 결재 삭제
    public void deleteApproval(int approvalNo) {
        String sql = "DELETE FROM approval WHERE approval_no = ?";
        Object[] data = {approvalNo};
        jdbcTemplate.update(sql, data);
    }

    // 결재 이력 조회
    public List<ApprovalDto> selectApprovalHistory(int documentNo) {
        String sql = "SELECT * FROM approval WHERE approval_document = ?";
        Object[] data = {documentNo};
        return jdbcTemplate.query(sql, approvalMapper, data);
    }

    // 추가된 메서드: approvalNo로 documentNo 조회
    public int findDocumentNoByApprovalNo(int approvalNo) {
        String sql = "SELECT approval_document FROM approval WHERE approval_no = ?";
        Object[] data = {approvalNo};
        return jdbcTemplate.queryForObject(sql, data, Integer.class);
    }
 // 결재자 설정 메서드 추가 예시
    public void saveApprovers(int documentId, List<String> approvers) {
        String sql = "INSERT INTO approvers (document_id, approver_emp_id) VALUES (?, ?)";
        for (String approver : approvers) {
            Object[] data = { documentId, approver };
            jdbcTemplate.update(sql, data);
        }
}
}