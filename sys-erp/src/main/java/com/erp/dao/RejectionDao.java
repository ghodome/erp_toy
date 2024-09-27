package com.erp.dao;





import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.erp.dto.RejectionDto;
@Mapper
public interface RejectionDao {

    void insertRejection(RejectionDto rejectionDto);

    RejectionDto getRejectionByDocument(@Param("rejectionDocument") int rejectionDocument);
}
