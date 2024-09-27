package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.RoomDto;

@Service
public class RoomMapper implements RowMapper<RoomDto> {
    
    @Override
    public RoomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        RoomDto roomDto = new RoomDto();
        roomDto.setRoomNo(rs.getInt("room_no")); 
        roomDto.setStartTime(rs.getString("start_time"));
        roomDto.setMaxPerson(rs.getString("max_person"));
        roomDto.setRoomName(rs.getString("room_name"));
        return roomDto;
    }
}
