package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Deal;
import org.java.demo.serv.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/deals")
public class DealController {
	
	@Autowired 
	private DealService dealServ;

	@GetMapping
	public String getDealIndex(Model model) {
		
		
		List<Deal> deals = dealServ.findAll();
		model.addAttribute("deals", deals);
		
		return "deal-index";
	}
	
	@GetMapping("/create")
	public String getDealCreateForm(Model model) {
		
		model.addAttribute("deal", new Deal());
		
		return "deal-create";
	}
	
	@PostMapping("/create")
	public String storeNewDeal(
			Model model,
			@Valid @ModelAttribute Deal deal,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			model.addAttribute("deal", deal);
			
			return "deal-create";
		}
		
		dealServ.save(deal);
		
		return "redirect:/deals";
	}
	
}
