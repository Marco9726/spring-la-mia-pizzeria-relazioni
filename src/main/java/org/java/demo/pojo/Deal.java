package org.java.demo.pojo;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Deal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@FutureOrPresent(message = "La data non può essere nel passato")
	private LocalDate dateStart;
	@FutureOrPresent(message = "La data non può essere nel passato")
	private LocalDate dateEnd;
	@NotBlank(message = "Il nome non può essere null")
	private String title;
	@NotNull
	@Min(value = 1, message = "la percentuale dello sconto deve essere almeno di 1")
	@Max(value = 100, message = "la percentuale dello sconto può essere al massimo 100")
	private Integer discount;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
	public Deal() {}
	
	public Deal(LocalDate dateStart, LocalDate dateEnd, String title, Integer discount, Pizza pizza) {
		
		setDateStart(dateStart);
		setDateEnd(dateEnd);
		setTitle(title);
		setDiscount(discount);
		setPizza(pizza);
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDate dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDate dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getTitle() + "\n" + getDateStart() + "-" + getDateEnd() + "\n" + getDiscount();
	}
	
}
