package com.grigor.pancakes_unlimited;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pancake")
@PancakeConstraint
public class Pancake {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
	
	@ManyToOne
	@JoinColumn(name="basic_ingredient",referencedColumnName = "id", nullable = false)
    @NotNull(message = "Basic ingredient is mandatory")
	private Ingredient basicIngredient;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "ingredients_list", 
			joinColumns = @JoinColumn(name = "pancake_id"), 
			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
	)
	private Set <Ingredient> ingredientsSet;
	
	
	public Pancake() {};
		
	public Pancake(Ingredient i) {
		this.basicIngredient=i;
		this.ingredientsSet = new HashSet<>();
	}	

	public long getId() {
		return id;
	}

	public Ingredient getBasicIngredient() {
		return basicIngredient;
	}

	public void setBasicIngredient(Ingredient basicIngredient) {
		this.basicIngredient = basicIngredient;
	}
	
	public Set<Ingredient> getIngredientsSet() {
		return ingredientsSet;
	}

	public void setIngredientsSet(Set<Ingredient> ingredientsSet) {
		this.ingredientsSet = ingredientsSet;
	}
	
}