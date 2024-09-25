package com.erp.restcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dto.RfidDto;

@RestController
@RequestMapping("/rest")
public class RFIDRestController {

	@PostMapping("/card")
	public String receiveCardValue(@RequestBody RfidDto rfidDto) {
		System.out.println("Received card id: " + rfidDto.getRfidId());
		System.out.println("Received card value: " + rfidDto.getRfidValue());
		return "Card value received successfully!";
	}

}
