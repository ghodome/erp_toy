package com.erp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dao.EmpDao;
import com.erp.dto.EmpDto;

@Service
public class EmpService {

    @Autowired
    private EmpDao empDao;
    
    @Autowired
    private SqlSession sqlSession;

    /**
     * 사원 정보 조회
     * @param empId 사원 ID
     * @return 사원 정보
     */
    public EmpDto getEmpById(String empId) {
        return empDao.selectOneById(empId);
    }

    /**
     * 사원 서명 확인
     * @param empId 사원 ID
     * @return 서명이 있는지 여부
     */
    public boolean checkEmpSignature(String empId) {
        EmpDto emp = getEmpById(empId);
        return emp.getEmpSignature() != null && !emp.getEmpSignature().isEmpty();
    }

    /**
     * 사원 서명 저장
     * @param empDto 사원 데이터 (서명 포함)
     */
    public void saveSignature(EmpDto empDto) {
        empDao.updateSignature(empDto);
    }

    /**
     * 사원 정보 등록
     * @param empDto 사원 정보
     */
    public void registerEmp(EmpDto empDto) {
        empDao.insert(empDto);
    }
    
    public EmpDto findEmpByNo(String empNo) {
        return sqlSession.selectOne("emp.selectEmpByNo", empNo);
    }

    public void updateEmp(EmpDto empDto) {
        sqlSession.update("emp.update", empDto);
    }

}

