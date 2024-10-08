package com.erp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.dao.PortDao;
import com.erp.dao.ProductDao;
import com.erp.dto.PortDto;
import com.erp.error.TargetNotFoundException;

@RestController
@CrossOrigin(origins= {"http://localhost:3000"})
@RequestMapping("/port")
public class PortRestController {
	
	@Autowired
	private PortDao portDao;
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/")
	public List<PortDto> list() {
		return portDao.selectList();
	}
	@GetMapping("/{portNo}")
	public PortDto one(@PathVariable int portNo) {
		PortDto portDto=portDao.selectOne(portNo);
		if(portDto==null) throw new TargetNotFoundException();
		return portDto;
	}
	@PostMapping("/")
	public void receive(@RequestBody PortDto portDto) {
		if(!(portDao.insert(portDto))) throw new TargetNotFoundException();
		productDao.receive(portDto.getPortNo());
	}
	@DeleteMapping("/")
	public void delete(@PathVariable int portNo) {
		portDao.delete(portNo);
	}
	
}
