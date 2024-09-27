package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.MeetingDto;



@Service
public class MeetingMapper implements RowMapper <MeetingDto>{
	
	@Override
	   public MeetingDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        MeetingDto meetingDto = new MeetingDto();
        meetingDto.setReservationNo(rs.getInt("reservation_no"));
        meetingDto.setStartTime(rs.getString("start_time"));
        meetingDto.setFinishTime(rs.getString("finish_time"));
        meetingDto.setEditTime(rs.getString("edit_time"));
        meetingDto.setEditStartTime(rs.getString("edit_start_time"));
        meetingDto.setEditFinishTime(rs.getString("edit_finish_time"));
        return meetingDto;
	}
}