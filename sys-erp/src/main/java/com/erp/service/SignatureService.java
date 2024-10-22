package com.erp.service;

import com.erp.dao.EmpDao;
import com.erp.dto.EmpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignatureService {

    @Autowired
    private EmpDao empDao;

    /**
     * 서명을 저장하거나 업데이트하는 메서드
     *
     * @param empNo 사원 번호
     * @param signatureData 서명 데이터 (Base64 형식)
     * @return 성공 여부
     */
    @Transactional
    public boolean saveOrUpdateSignature(String empNo, String signatureData) {
        // 서명 데이터 검증
        if (signatureData == null || !signatureData.startsWith("data:image/")) {
            throw new IllegalArgumentException("올바른 서명 데이터를 제공해 주세요.");
        }

        // 사원 정보 조회
        EmpDto empDto = empDao.selectOneByNo(empNo);
        if (empDto == null) {
            throw new IllegalArgumentException("사원을 찾을 수 없습니다.");
        }

        // 서명 저장 또는 업데이트
        empDto.setEmpSignature(signatureData);
        empDao.updateSignature(empDto);
        return true;
    }

    /**
     * 특정 사원의 서명을 조회하는 메서드
     *
     * @param empNo 사원 번호
     * @return 서명 데이터 (Base64 형식)
     */
    public String getSignature(String empNo) {
        EmpDto empDto = empDao.selectOneByNo(empNo);
        if (empDto == null) {
            throw new IllegalArgumentException("사원을 찾을 수 없습니다.");
        }
        return empDto.getEmpSignature();
    }
}
