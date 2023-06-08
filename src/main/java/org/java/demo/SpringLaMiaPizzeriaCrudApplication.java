package org.java.demo;

import java.time.LocalDate;

import org.java.demo.pojo.Deal;
import org.java.demo.pojo.Ingredient;
import org.java.demo.pojo.Pizza;
import org.java.demo.serv.DealService;
import org.java.demo.serv.IngredientService;
import org.java.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DealService dealService;
	
	@Autowired
	private IngredientService ingredientService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Ingredient i1 = new Ingredient("pomodoro");
		Ingredient i2 = new Ingredient("mozzarella");
		Ingredient i3 = new Ingredient("salsiccia");
		
		ingredientService.save(i1);
		ingredientService.save(i2);
		ingredientService.save(i3);
		
		Pizza p1 = new Pizza("margherita", "il grande classico",
				"https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 8, i1, i2);
		
		Pizza p2 = new Pizza("boscaiola", "un ottimo contrasto",
				"https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 9);
		
		Pizza p3 = new Pizza("napoli", "molto saporita",
				"https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 6, i1, i3);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		
		Deal d1 = new Deal(LocalDate.now(), LocalDate.parse("2023-06-10"), "sconto1", 10, p1);
		Deal d2 = new Deal(LocalDate.now(), LocalDate.parse("2023-06-10"), "sconto2", 20, p2);
		Deal d3 = new Deal(LocalDate.now(), LocalDate.parse("2023-06-10"), "sconto3", 30, p3);
		
		dealService.save(d1);
		dealService.save(d2);
		dealService.save(d3);
		

		
		
	}
}
