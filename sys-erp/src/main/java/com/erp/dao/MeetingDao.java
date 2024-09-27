package com.erp.dao;

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

    // 회의실 예약 등록
    public void insert(MeetingDto meetingDto) {
        String sql = "INSERT INTO meeting ("
                + "reservation_no, start_time, finish_time, edit_time, edit_start_time, edit_finish_time"
                + ") VALUES (reservation_seq.nextval, ?, ?, ?, ?, ?)";
        Object[] data = {
            meetingDto.getStartTime(),
            meetingDto.getFinishTime(),
            meetingDto.getEditTime(),
            meetingDto.getEditStartTime(),
            meetingDto.getEditFinishTime()
        };
        jdbcTemplate.update(sql, data);
    }

    // 회의실 예약 수정
    public boolean update(MeetingDto meetingDto) {
        String sql = "UPDATE meeting SET "
                + "start_time = ?, finish_time = ?, edit_time = ?, edit_start_time = ?, edit_finish_time = ? "
                + "WHERE reservation_no = ?";
        Object[] data = {
            meetingDto.getStartTime(),
            meetingDto.getFinishTime(),
            meetingDto.getEditTime(),
            meetingDto.getEditStartTime(),
            meetingDto.getEditFinishTime(),
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
}
