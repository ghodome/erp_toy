package com.erp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.erp.dto.EmpDto;
import com.erp.mapper.EmpMapper;

@Repository
public class EmpDao {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlSession sqlSession; // MyBatis SqlSession 추가

    // JdbcTemplate 기반 메서드 (유지)
    public void updateSignature(EmpDto empDto) {
        String sql = "UPDATE emp SET emp_signature = ? WHERE emp_id = ?";
        Object[] data = {
            empDto.getEmpSignature(),
            empDto.getEmpId()
        };
        jdbcTemplate.update(sql, data);
    }

    // JdbcTemplate 기반 메서드 (유지)
    public void insert(EmpDto empDto) {
        String sql = "INSERT INTO emp ("
                + "emp_id, emp_password, emp_name, "
                + "emp_level, emp_dept, emp_gender, emp_hp, emp_email, emp_birth, emp_sdate, "
                + "emp_edu, emp_post, emp_address1, emp_address2) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        String encPw = encoder.encode(empDto.getEmpPassword()); // 비밀번호 해싱
        empDto.setEmpPassword(encPw); // 해싱된 비밀번호로 설정

        Object[] data = {
            empDto.getEmpId(), empDto.getEmpPassword(), empDto.getEmpName(), empDto.getEmpLevel(),
            empDto.getEmpDept(), empDto.getEmpGender(), empDto.getEmpHp(), empDto.getEmpEmail(),
            empDto.getEmpBirth(), empDto.getEmpSdate(), empDto.getEmpEdu(),
            empDto.getEmpPost(), empDto.getEmpAddress1(), empDto.getEmpAddress2()
        };

        jdbcTemplate.update(sql, data);
    }

    // JdbcTemplate 기반 메서드 (유지)
    public EmpDto selectOneById(String empId) {
        String sql = "SELECT * FROM EMP WHERE EMP_ID = ?";
        Object[] data = { empId };
        List<EmpDto> list = jdbcTemplate.query(sql, empMapper, data);
        return list.isEmpty() ? null : list.get(0);
    }

  

    // MyBatis 기반 메서드 (추가)
    public EmpDto selectOneByIdMyBatis(String empId) {
        return sqlSession.selectOne("emp.selectEmpById", empId);
    }

    // MyBatis 기반 메서드 (추가)
    public void updateSignatureMyBatis(EmpDto empDto) {
        sqlSession.update("emp.update", empDto);
    }

    // MyBatis 기반 메서드 (추가) - 사원 번호로 정보 조회
    public EmpDto selectOneByNoMyBatis(String empNo) {
        return sqlSession.selectOne("emp.selectEmpByNo", empNo);
    }
}
