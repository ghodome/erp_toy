package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.ChatroomDto;

@Service
public class ChatroomMapper implements RowMapper<ChatroomDto> {

	@Override
	public ChatroomDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChatroomDto chatroomDto = new ChatroomDto();
		
		return null;
	}

}
