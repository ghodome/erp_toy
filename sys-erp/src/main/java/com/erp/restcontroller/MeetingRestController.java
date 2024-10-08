package com.erp.restcontroller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.MeetingDao;
import com.erp.dao.RoomDao;
import com.erp.dto.MeetingDto;
import com.erp.dto.RoomDto;


@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
@RequestMapping("/meeting")
public class MeetingRestController {
	@Autowired
	private MeetingDao meetingDao;
	@Autowired
	private RoomDao roomDao;

	// 예약 등록 POST
	@PostMapping("/")
	public void insert(@RequestBody MeetingDto meetingDto) {
		meetingDao.insert(meetingDto);
	}
	
	// 모든 회의실 예약 목록 조회 GET
	@GetMapping ("/")
	List<MeetingDto> list(){
		return meetingDao.selectList();
	}
	
	// 예약 번호로 회의실 예약 목록 조회 GET
	@GetMapping("/{reservationNo}")
	public MeetingDto detail (@PathVariable int reservationNo) {
		MeetingDto meetingDto = meetingDao.selectOne(reservationNo);
//		if (meetingDto == null) {
//			throw new TargetNotFoundException();
//		}
		return meetingDto;
	}
	
	// 기간별 회의실 예약 조회 GET
	@GetMapping("/period/start/{startTime}/end/{endTime}")
	public List<MeetingDto> searchByPeriod(
			@PathVariable String startTime,
			@PathVariable String endTime){
		return meetingDao.selectByPeriod(startTime, endTime);
	}
	
	// 회의실 예약 수정 PUT 
	@PutMapping("/")
	public void update(@RequestBody MeetingDto meetingDto) {
		boolean result =meetingDao.update(meetingDto);
//		if (!result) {
//			throw new TargetNotFoundException();
//		}
	}
	
	// 회의실 예약 삭제 DELETE
	@DeleteMapping("/{reservationNo}")
	public void delete(@PathVariable int reservationNo) {
		boolean result = meetingDao.delete(reservationNo);
//		if (!result) {
//			throw new TargetNotFoundException(); 
//		}
	}
	
	// 해당 회의실 조회 GET
	@GetMapping("/rooms/{roomNo}")
	public RoomDto getRoom(@PathVariable int roomNo) {
		return roomDao.selectOne(roomNo);
	}
	
}