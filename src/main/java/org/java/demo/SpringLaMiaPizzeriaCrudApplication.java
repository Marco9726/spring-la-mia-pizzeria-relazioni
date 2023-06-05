package org.java.demo;

import org.java.demo.pojo.Pizza;
import org.java.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Pizza p1 = new Pizza("margherita", "il grande classico",
				"https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 8);
		
		Pizza p2 = new Pizza("boscaiola", "un ottimo contrasto",
				"https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 9);
		
		Pizza p3 = new Pizza("napoli", "molto saporita",
				"https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 6);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		
		
	}
}
