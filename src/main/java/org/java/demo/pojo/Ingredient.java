package org.java.demo.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingredient {





	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	private String name;
	
	@ManyToMany(mappedBy = "ingredients")
	public List<Pizza> pizze;
	
	public Ingredient() {}
	
	public Ingredient(String name) {
		
		setName(name);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Pizza> getPizze() {
		return pizze;
	}
	
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getId() + "-" + getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Ingredient)) return false;
		
		Ingredient objIngr = (Ingredient) obj;
		
		return getId() == objIngr.getId();
	}
}
