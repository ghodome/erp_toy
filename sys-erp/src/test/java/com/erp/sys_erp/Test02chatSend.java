package com.erp.sys_erp;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.erp.dao.ChatDao;
import com.erp.dto.ChatDto;

@SpringBootTest
public class Test02chatSend {

	@Autowired
	private ChatDao chatDao;
	
	@Test
	public void test() {
		ChatDto chatDto = new ChatDto();
		chatDto.setChatId("test12");
		chatDto.setChatContent("반가워용3");
		chatDto.setChatTime(new Date(0));
		chatDto.setChatroomNo(1);
		
		chatDao.insert(chatDto);
		
	}
}
