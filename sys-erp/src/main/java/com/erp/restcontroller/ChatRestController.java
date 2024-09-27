package com.erp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.ChatDao;
import com.erp.dto.ChatDto;

@CrossOrigin(origins= {"http://localhost:3000"})
@RestController
@RequestMapping("/chat")
public class ChatRestController {

	@Autowired
	private ChatDao chatDao;
	
	
	@GetMapping("/")
	public List<ChatDto> list(){
		return chatDao.selectList();
	}
	
	@GetMapping("/keyword/{keyword}")
	public List<ChatDto> chatroomContent(@PathVariable int keyword){
		List<ChatDto> list = chatDao.selectListByChatroomNo(keyword);
		return list;
	}
}
