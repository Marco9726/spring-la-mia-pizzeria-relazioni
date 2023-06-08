package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Ingredient;
import org.java.demo.pojo.Pizza;
import org.java.demo.serv.IngredientService;
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
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired 
	private IngredientService ingrServ;
	
	@Autowired 
	private PizzaService pizzaServ;

	@GetMapping
	public String getIngredientIndex(Model model) {
		
		
		List<Ingredient> ingredients = ingrServ.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "ingredient-index";
	}
	
	@GetMapping("/create")
	public String getIngredientCreateForm(Model model) {
		
		model.addAttribute("ingredient", new Ingredient());
		
		return "ingredient-create";
	}
	
	@PostMapping("/create")
	public String storeNewIngredient(
			Model model,
			@Valid @ModelAttribute Ingredient ingredient,
			BindingResult bindingResult
			) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println(err.getDefaultMessage());
			
			model.addAttribute("ingredient", ingredient);
			
			return "ingredient-create";
		}
		
		ingrServ.save(ingredient);
		
		return "redirect:/ingredients";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteIngredient(
			@PathVariable int id
			) {
		
		Ingredient ing = ingrServ.findById(id).get();
		
		for ( Pizza p : ing.getPizze()) {
			
			p.removeIngredient(ing);
			pizzaServ.save(p);
		}
		
		ingrServ.delete(ing);
		
		return "redirect:/ingredients";
	}
}
