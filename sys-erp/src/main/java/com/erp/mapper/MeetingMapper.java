package com.erp.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.MeetingDto;



@Service
public class MeetingMapper implements RowMapper <MeetingDto>{
	
    @Override
    public MeetingDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        MeetingDto meetingDto = new MeetingDto();
        
        
        meetingDto.setReservationNo(rs.getInt("reservation_no"));
        meetingDto.setEmpId(rs.getString("emp_id"));
        
        // Timestamp -> Date 변환
        Timestamp startTimestamp = rs.getTimestamp("start_time");
        Timestamp finishTimestamp = rs.getTimestamp("finish_time");
        Timestamp editTime = rs.getTimestamp("edit_time");
        Timestamp editStartTime = rs.getTimestamp("edit_start_time");
        Timestamp editFinishTime = rs.getTimestamp("edit_finish_time");

        meetingDto.setStartTime(startTimestamp != null ? new Date(startTimestamp.getTime()) : null);
        meetingDto.setFinishTime(finishTimestamp != null ? new Date(finishTimestamp.getTime()) : null);
        meetingDto.setEditTime(editTime != null ? new Date(editTime.getTime()) : null);
        meetingDto.setEditStartTime(editStartTime != null ? new Date(editStartTime.getTime()) : null);
        meetingDto.setEditFinishTime(editFinishTime != null ? new Date(editFinishTime.getTime()) : null);
        
//        meetingDto.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());  // Timestamp로 가져온 후 LocalDateTime 변환
//        meetingDto.setFinishTime(rs.getTimestamp("finish_time").toLocalDateTime());  // 마찬가지로 변환
//        meetingDto.setEditTime(rs.getTimestamp("edit_time").toLocalDateTime());
//        meetingDto.setEditStartTime(rs.getTimestamp("edit_start_time").toLocalDateTime());
//        meetingDto.setEditFinishTime(rs.getTimestamp("edit_finish_time").toLocalDateTime());

        //        meetingDto.setStartTime(rs.getDate("start_time"));
//        meetingDto.setFinishTime(rs.getDate("finish_time"));
//        meetingDto.setEditTime(rs.getDate("edit_time"));
//        meetingDto.setEditStartTime(rs.getDate("edit_start_time"));
//        meetingDto.setEditFinishTime(rs.getDate("edit_finish_time"));
        
        
        
        
        meetingDto.setRoomName(rs.getString("room_name"));
        meetingDto.setPurpose(rs.getString("purpose"));
        return meetingDto;
    }
}