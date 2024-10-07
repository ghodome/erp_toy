package com.erp.service;

import com.erp.dao.RejectionDao;
import com.erp.dto.RejectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RejectionService {

    @Autowired
    private RejectionDao rejectionDao;

    public void saveRejection(RejectionDto rejectionDto) {
        rejectionDao.insert(rejectionDto);
    }

    public List<RejectionDto> getRejectionsByDocument(int documentNo) {
        return rejectionDao.selectListByDocument(documentNo);
    }
}
