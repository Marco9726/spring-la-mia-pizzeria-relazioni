package org.java.demo.serv;

import java.util.List;

import org.java.demo.pojo.Deal;
import org.java.demo.repo.DealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {

	@Autowired
	private DealRepo dealRepo;
	
	public List<Deal> findAll() {
		
		return dealRepo.findAll();
	}
	
	public Deal save(Deal deal) {
		
		return dealRepo.save(deal);
	}
}
