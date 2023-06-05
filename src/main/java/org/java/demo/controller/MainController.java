package org.java.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Pizza;
import org.java.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/")
	public String getPizzaIndex(Model model) {
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "pizza-index";
	}
	
	@GetMapping("/pizze/{id}")
	public String getPizzaShow(
				Model model,
				@PathVariable int id
				) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}
	
	@PostMapping("pizze/search")
	public String getPizzeByName(
			Model model,
			@RequestParam("search") String name
			) {
		
		List<Pizza> pizze = pizzaService.findByName(name);
		model.addAttribute("pizze", pizze);
		
		return "pizza-index";
	}
	
	@GetMapping("/pizze/new")
	public String createNewPizza(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "pizza-new";
	}
	
	@PostMapping("pizze/new")
	public String storeNewPizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult
			)  {
		if (bindingResult.hasErrors()) {
			
			for ( ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "pizza-new";
		}
		
		pizzaService.save(pizza);		
		return "redirect:/";
	}
	
	@GetMapping("/pizze/update/{id}")
	public String getPizzaUpdate(
			Model model,
			@PathVariable int id
			) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza-update";
		
	}
	
	@PostMapping("/pizze/update/{id}")
	public String storePizzaUpdated(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			
			for ( ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "pizza-update";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/";
		
	}

	@GetMapping("/pizze/delete/{id}")
	public String deletePizza(
			@PathVariable int id
			) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
		
	}
		
}
