package com.grigor.pancakes_unlimited;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


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
	
	@Column(name="healthy")
    @NotNull(message = "Field healthy is mandatory")
	private Boolean healthy;
	
	
	public Ingredient() {};
	
	public Ingredient(String name, double price, String category, boolean healthy) {
		this.name = name;
		this.price = price;
		this.category=category;
		this.healthy=healthy;
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
		
	 public boolean isHealthy() {
		return healthy;
	}

	public void setHealthy(boolean healthy) {
		this.healthy = healthy;
	}

	@Override
	    public boolean equals(Object o) {
		 if (o==null)
			 return false;
	     if (o == this)
	         return true;
	     if (!(o instanceof Ingredient))
	         return false;
	     Ingredient other = (Ingredient) o;
	     return Objects.equals(this.id, other.id);
	 }
	 
	 @Override
	 public final int hashCode() {
		 int prime = 31;
		 int result = 1;
		 result = prime * result + ((id == 0) ? 0 : (int)id);
		 return result;
	 }
}
