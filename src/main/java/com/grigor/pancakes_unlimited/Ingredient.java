package com.grigor.pancakes_unlimited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@IngredientConstraint
@Table(name="ingredient")
public class Ingredient {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
    @NotBlank(message = "Name is mandatory")
	private String name;
	
	@Column(name="price")
	private double price;
	
	@Column(name="category")
    @NotBlank(message = "Category is mandatory")
	@CategoryConstraint(enumClass = CategoryEnum.class)
	private String category;
	
	
	public Ingredient() {};
	
	public Ingredient(String name, double price, String category) {
		this.name = name;
		this.price = price;
		this.category=category;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
