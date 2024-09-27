package com.erp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.erp.dto.ChatDto;

@Service
public class ChatMapper implements RowMapper<ChatDto> {

	@Override
	public ChatDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		ChatDto chatDto = new ChatDto();
		
		chatDto.setChatNo(rs.getInt("chat_no"));
		// 매퍼 수정완료
		chatDto.setChatChatroomNo(rs.getInt("chatroom_no"));
		chatDto.setChatId(rs.getString("chat_id"));
		chatDto.setChatContent(rs.getString("chat_content"));
		chatDto.setChatRead(rs.getInt("chat_read"));
		chatDto.setChatTime(rs.getDate("chat_time"));
		chatDto.setChatDelete(rs.getString("chat_delete"));
		
		return chatDto;
	}

}
