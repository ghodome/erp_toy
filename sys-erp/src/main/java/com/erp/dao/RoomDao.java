package com.erp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.erp.dto.RoomDto;
import com.erp.mapper.RoomMapper;

@Repository
public class RoomDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoomMapper roomMapper;

    // 회의실 시퀀스 생성
    public int sequence() {
        String sql = "SELECT room_seq.nextval FROM dual";
        return jdbcTemplate.queryForObject(sql, int.class);
    }
    // 모든 회의실 조회 meeting? room? 둘중하나삭제 
    public List<RoomDto> selectList() {
        String sql = "SELECT * FROM room ORDER BY room_no ASC";
        return jdbcTemplate.query(sql, roomMapper);
    }

    // 특정 회의실 조회 (room_no로 조회)
    public RoomDto selectOne(int roomNo) {
        String sql = "SELECT * FROM room WHERE room_no = ?";
        Object[] data = {roomNo};
        List<RoomDto> list = jdbcTemplate.query(sql, roomMapper, data);
        return list.isEmpty() ? null : list.get(0);
    }

    // 최대 수용 인원에 따른 회의실 조회
    public List<RoomDto> selectByMaxPerson(String maxPerson) {
        String sql = "SELECT * FROM room WHERE max_person = ? ORDER BY room_no ASC";
        Object[] data = {maxPerson};
        return jdbcTemplate.query(sql, roomMapper, data);
    }
}
