package com.erp.service;

import com.erp.dao.RejectionDao;
import com.erp.dto.RejectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RejectionService {

    @Autowired
    private RejectionDao rejectionDao;

    @Transactional
    public void saveRejection(RejectionDto rejectionDto) {
        rejectionDao.insert(rejectionDto);
    }

    @Transactional(readOnly = true)
    public List<RejectionDto> getRejectionsByDocument(int documentNo) {
        return rejectionDao.selectByDocument(documentNo);
    }

    @Transactional(readOnly = true)
    public List<RejectionDto> getRejectionsByEmployee(String empId) {
        return rejectionDao.selectByEmployee(empId);
    }

    @Transactional
    public void updateRejectionReason(int rejectionNo, String reason) {
        rejectionDao.updateRejectionReason(rejectionNo, reason);
    }

    @Transactional(readOnly = true)
    public List<RejectionDto> selectAll() {
        return rejectionDao.selectAll(); // selectAll 메소드 추가
    }

    @Transactional(readOnly = true)
    public List<String> getRejectionReasonsStatistics() {
        return rejectionDao.getRejectionReasonsStatistics(); // 이 메소드는 dao에서 구현되어야 함
    }

    @Transactional(readOnly = true)
    public int countRejectedDocuments(String empId) {
        return rejectionDao.countRejectedDocuments(empId);
    }
}
