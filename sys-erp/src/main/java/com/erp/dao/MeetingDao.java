package com.erp.dao;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.dto.MeetingDto;
import com.erp.mapper.MeetingMapper;

@Repository
public class MeetingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private MeetingMapper meetingMapper;


    public void insert(MeetingDto meetingDto) {
        String sql = "INSERT INTO meeting (reservation_no, emp_id, room_name, start_time, finish_time, edit_time, edit_start_time, edit_finish_time,purpose) "
                   + "VALUES (meeting_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] data = {
        		meetingDto.getEmpId(), 
        		meetingDto.getRoomName(),
//        		meetingDto.getStartTime(),
//        		meetingDto.getFinishTime(),
//        		meetingDto.getEditTime(),
//            meetingDto.getEditStartTime(),
//            meetingDto.getEditFinishTime(),
        		 new Timestamp(meetingDto.getStartTime().getTime()),  // Date -> Timestamp
        	        new Timestamp(meetingDto.getFinishTime().getTime()), // Date -> Timestamp
        	        meetingDto.getEditTime() != null ? new Timestamp(meetingDto.getEditTime().getTime()) : null,
        	        meetingDto.getEditStartTime() != null ? new Timestamp(meetingDto.getEditStartTime().getTime()) : null,
        	        meetingDto.getEditFinishTime() != null ? new Timestamp(meetingDto.getEditFinishTime().getTime()) : null,
            meetingDto.getPurpose(),
        };
        jdbcTemplate.update(sql, data);
    }


    // 회의실 예약 수정
    public boolean update(MeetingDto meetingDto) {
        String sql = "UPDATE meeting SET "
                + "start_time = ?, finish_time = ?, "
                + "edit_start_time = ?, edit_finish_time = ?, "
                + "room_name = ?, purpose = ? "
                + "WHERE reservation_no = ?";
        
        Object[] data = {
//        		meetingDto.getStartTime(),
//        		meetingDto.getFinishTime(),
//        		meetingDto.getEditStartTime(),
//        		meetingDto.getEditFinishTime(),
        		 new Timestamp(meetingDto.getStartTime().getTime()),  // Date -> Timestamp
        	        new Timestamp(meetingDto.getFinishTime().getTime()), // Date -> Timestamp
        	        meetingDto.getEditStartTime() != null ? new Timestamp(meetingDto.getEditStartTime().getTime()) : null,
        	        meetingDto.getEditFinishTime() != null ? new Timestamp(meetingDto.getEditFinishTime().getTime()) : null,
        		
        		meetingDto.getRoomName(),
        		meetingDto.getPurpose(),
        		meetingDto.getReservationNo()
        };
        
        return jdbcTemplate.update(sql, data) > 0;
    }



    // 회의실 예약 삭제
    public boolean delete(int reservationNo) {
        String sql = "DELETE FROM meeting WHERE reservation_no = ?";
        Object[] data = {reservationNo};
        return jdbcTemplate.update(sql, data) > 0;
    }

    // 모든 회의실 예약 조회
    public List<MeetingDto> selectList() {
        String sql = "SELECT * FROM meeting ORDER BY reservation_no ASC";
        return jdbcTemplate.query(sql, meetingMapper);
    }
//    public List<MeetingDto> findAll() {
//        String sql = "SELECT * FROM meeting";
//        return jdbcTemplate.query(sql, new MeetingMapper());
//    }
    
//    public List<MeetingDto> findAll() {
//    	String sql = "SELECT m.reservation_no, m.emp_id, m.room_no, m.start_time, m.finish_time, r.room_name " +
//    	             "FROM meeting m " +
//    	             "JOIN room r ON m.room_no = r.room_no";
//
//    	List<MeetingDto> meetingList = jdbcTemplate.query(sql, new MeetingMapper());
//
//        return jdbcTemplate.query(sql, new MeetingMapper());
//    }

    
    
    
    


    // 예약 번호로 회의실 예약 조회
    public MeetingDto selectOne(int reservationNo) {
        String sql = "SELECT * FROM meeting WHERE reservation_no = ?";
        Object[] data = {reservationNo};
        List<MeetingDto> list = jdbcTemplate.query(sql, meetingMapper, data);
        return list.isEmpty() ? null : list.get(0);
    }
    
    // 기간별 회의실 예약 조회 (시작 시간과 끝 시간으로 필터링)
    public List<MeetingDto> selectByPeriod(String startTime, String endTime) {
        String sql = "SELECT * FROM meeting WHERE start_time >= ? AND finish_time <= ? ORDER BY start_time ASC";
        Object[] data = {startTime, endTime};
        return jdbcTemplate.query(sql, meetingMapper, data);
    }

    // 예약 시퀀스 생성
    public int sequence() {
        String sql = "SELECT reservation_seq.nextval FROM dual";
        return jdbcTemplate.queryForObject(sql, int.class);
    }
    
    
    //예약 자동삭제
//        public int deleteExpiredMeetings() {
//            String sql = "DELETE FROM meeting WHERE finish_time < NOW()";
//            return jdbcTemplate.update(sql);
//        }

//    public void deleteExpiredMeetings() {
//        String sql = "DELETE FROM meeting WHERE finish_time < ?";
//        Date now = (Date) new java.util.Date();  // 현재 시각을 Date로 생성
//        
//        Object[] params = {new Timestamp(now.getTime())}; // Date -> Timestamp
//        int deletedRows = jdbcTemplate.update(sql, params);
//
//        if (deletedRows > 0) {
//            System.out.println(deletedRows + "개의 만료된 예약이 삭제되었습니다.");
//        } else {
//            System.out.println("삭제할 만료된 예약이 없습니다.");
//        }
//    }
    
    public void deleteExpiredMeetings() {
        String sql = "DELETE FROM meeting WHERE finish_time < ?";
        
        // 현재 시각을 ZonedDateTime으로 생성
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        Object[] params = {Timestamp.valueOf(now.toLocalDateTime())}; // Date -> Timestamp

        int deletedRows = jdbcTemplate.update(sql, params);

        if (deletedRows > 0) {
            System.out.println(deletedRows + "개의 만료된 예약이 삭제되었습니다.");
        } else {
            System.out.println("삭제할 만료된 예약이 없습니다.");
        }
    }


    
    
    
    
    
}
