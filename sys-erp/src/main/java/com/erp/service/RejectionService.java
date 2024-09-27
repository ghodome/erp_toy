package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.erp.dao.RejectionDao;
import com.erp.dto.RejectionDto;

@Service
public class RejectionService {

    @Autowired
    private RejectionDao rejectionDao;

    public void addRejection(RejectionDto rejectionDto) {
        rejectionDao.insertRejection(rejectionDto);
    }

    public RejectionDto findRejectionByDocument(int rejectionDocument) {
        return rejectionDao.getRejectionByDocument(rejectionDocument);
    }
}
