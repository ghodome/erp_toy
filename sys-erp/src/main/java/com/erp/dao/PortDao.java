package com.erp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.erp.dto.PortDto;

@Repository
public class PortDao {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(PortDto portDto) {
		return sqlSession.insert("port.reveive",portDto)>0;
	}
	public List<PortDto> selectList() {
		List<PortDto> list = sqlSession.selectList("port.list");
		return list;
	}
	public PortDto selectOne(@PathVariable int portNo){
		PortDto portDto=sqlSession.selectOne("port.one",portNo);
		return portDto;
	}
	public boolean delete(int portNo) {
		return sqlSession.delete("port.delete",portNo)>0;
	}
}
