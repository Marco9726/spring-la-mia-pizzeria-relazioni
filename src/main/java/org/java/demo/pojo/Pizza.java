package org.java.demo.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Size(min = 3, message = "il nome deve essere di almeno 3 caratteri")
	private String name;
	@NotBlank
	@Size(min = 5)
	private String description;
	@NotBlank
	private String imgUrl;
	@Min(0)
	@NotNull
	private Integer price;
	
	@OneToMany(mappedBy = "pizza")
	private List<Deal> deals;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza () { }
	
	public Pizza(String name, String description, String imgUrl, Integer price, Ingredient...ingredients ) {
		
		setName(name);
		setDescription(description);
		setImgUrl(imgUrl);
		setPrice(price);
		setIngredients(ingredients);
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public List<Deal> getDeals() {
		return deals;
	}
	
	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setIngredients(Ingredient[] ingredients) {
		
		setIngredients(Arrays.asList(ingredients));
	}
	
	public void addIngredient(Ingredient ingredient) {
		
		getIngredients().add(ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		
		getIngredients().remove(ingredient);
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + getId() + getName() + getDescription() + getPrice();
	}
}
