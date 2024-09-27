package com.erp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.dto.ChatDto;
import com.erp.mapper.ChatMapper;

@Repository
public class ChatDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ChatMapper chatMapper;
	
	public void insert(ChatDto chatDto) {
		String sql = "insert into chat("
				+ "chat_no, chatroom_no, chat_id, chat_content, chat_read, "
				+ "chat_time, chat_delete) "
				+ "values(chat_seq.nextval, ?, ?, ?, ?, ?, ?)";
		Object[] data = {
			chatDto.getChatroomNo(), chatDto.getChatId(), chatDto.getChatContent(), 
			chatDto.getChatRead(), chatDto.getChatTime(), chatDto.getChatDelete()
		};
		jdbcTemplate.update(sql, data);
	}
	public List<ChatDto> selectList(){
		String sql = "select * from chat order by chat_no asc";
		return jdbcTemplate.query(sql, chatMapper);
	}
	
	public List<ChatDto> selectListByChatroomNo(int keyword) {
	    String sql = "select * from chat where chatroom_no = ? "
	    		+ "order by chat_no asc";
	    Object[] data = {keyword};
	    return jdbcTemplate.query(sql, chatMapper, data);
	}
}
