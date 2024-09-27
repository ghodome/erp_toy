package com.erp.sys_erp;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.erp.dao.ChatDao;
import com.erp.dto.ChatDto;

@SpringBootTest
public class Test01chatTest {

	@Autowired
	private ChatDao chatDao;
	
	@Test
	public void test() {
		List<ChatDto> list = chatDao.selectList();
		for(ChatDto chatDto : list) {
			System.out.println(chatDto);
		}
	}
}
