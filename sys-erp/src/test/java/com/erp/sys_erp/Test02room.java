package com.erp.sys_erp;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.erp.dao.MeetingDao;
import com.erp.dto.MeetingDto;

@SpringBootTest
public class Test02room {

	@Autowired
	private MeetingDao meetingDao;
	@Test
	public void test() {
		List<MeetingDto> list = meetingDao.selectList();
			for(MeetingDto meetingDto : list) {
				System.out.println(meetingDto);
			}
			
			
		
	}
}
