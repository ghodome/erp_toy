package com.erp.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void receive(int productNo) {
		sqlSession.update("product.receive",productNo);
	}
	public void send(int productNo) {
		sqlSession.update("product.send",productNo);
	}
	
}
