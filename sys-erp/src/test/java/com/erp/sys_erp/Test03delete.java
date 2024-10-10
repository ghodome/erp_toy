package com.erp.sys_erp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.erp.dao.MeetingDao;
import com.erp.dto.MeetingDto;

@SpringBootTest
public class Test03delete {
	@Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private MeetingDao meetingDao;

    @Test
    public void testAutoDelete() {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setEmpId("emp001");
        meetingDto.setRoomName("Room A");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -2);
        Timestamp pastStartTime = new Timestamp(cal.getTimeInMillis());
        cal.add(Calendar.HOUR, 2); 
        Timestamp pastFinishTime = new Timestamp(cal.getTimeInMillis());
        meetingDto.setStartTime(new Date(pastStartTime.getTime()));  
        meetingDto.setFinishTime(new Date(pastFinishTime.getTime())); 
        meetingDto.setPurpose("만료된 테스트 예약");
        meetingDao.insert(meetingDto);
        meetingDao.deleteExpiredMeetings();
        List<MeetingDto> meetings = meetingDao.selectList();
        for (MeetingDto meeting : meetings) {
            System.out.println(meeting);
        }
        assert meetings.stream().noneMatch(m -> m.getEmpId().equals("emp001") && m.getRoomName().equals("Room A"));
    }
}
