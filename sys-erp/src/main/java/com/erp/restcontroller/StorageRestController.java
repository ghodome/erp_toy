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

import com.erp.dao.StorageDao;
import com.erp.dto.StorageDto;
import com.erp.error.TargetNotFoundException;

@RestController
@RequestMapping("/storage")
@CrossOrigin (origins={"http://localhost:3030"})
public class StorageRestController {
	
	@Autowired
	private StorageDao storageDao;
	
    @PostMapping("/")
    public void regist(@RequestBody StorageDto storageDto) {
        boolean isAdded = storageDao.insert(storageDto);
        if (!isAdded) 
            throw new TargetNotFoundException();
    }

    @GetMapping("/")
    public List<StorageDto> list() {
        List<StorageDto> storageList = storageDao.selectList();
        if(storageList==null) throw new TargetNotFoundException();
        return storageList;
    }
 
    @GetMapping("/{storageNo}")
    public StorageDto one(@PathVariable int storageNo) {
        StorageDto storageDto = storageDao.selectOne(storageNo);
        if (storageDto == null)
        	throw new TargetNotFoundException();
        return storageDto;
    }

    @PutMapping("/")
    public void updateStorage(@RequestBody StorageDto storageDto) {
        boolean isUpdated = storageDao.update(storageDto);
        if (!isUpdated) {
        	throw new TargetNotFoundException();
        }
    }

    @DeleteMapping("/{storageNo}")
    public void deleteStorage(@PathVariable int storageNo) {
        boolean isDeleted = storageDao.delete(storageNo);
        if (!isDeleted) {
        	throw new TargetNotFoundException();
        }
        
    }
}