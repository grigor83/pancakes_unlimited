package com.grigor.pancakes_unlimited;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderValidator implements ConstraintValidator<OrderConstraint, Order> {
	@Autowired
	PancakeRepo pRepo;
	boolean baseIngredient;
	boolean fillIngredient;

	@Override
	public boolean isValid(Order order, ConstraintValidatorContext context) {
		if (order.getPancakes()==null || order.getPancakes().size()==0)
			return false;
				
		Optional<Pancake> p;
		
		for (Pancake pancake : order.getPancakes()) {
			p= pRepo.findById(pancake.getId());			
			if(!p.isPresent())
				return false;
			
			//base ingredient is mandatory
			containingBaseIngredient(p.get());
			if (baseIngredient==false) {
				return false;
			}
						
			containingFillingIngredient(p.get());
			if (fillIngredient==false){
				return false;
			}
		}
		
		
		LocalDateTime localDateTime = LocalDateTime.now();
		order.setTime(localDateTime);
		return true;
	}
	
	private void containingBaseIngredient (Pancake pancake) {
		baseIngredient=false;
		
		if (pancake.getBasicIngredient().getCategory().equals("BASE"))
			if (baseIngredient==false)
				baseIngredient=true;
		
		for (Ingredient ingredient : pancake.getIngredientsSet()) {
			if (ingredient.getCategory().equals("BASE"))
				if (baseIngredient==false)
					baseIngredient=true;
				else
					return;
		}
	}
	
	private void containingFillingIngredient(Pancake pancake) {
		fillIngredient=false;
		
		if (pancake.getBasicIngredient().getCategory().equals("FILLING"))
			fillIngredient=true;	
		
		for (Ingredient ingredient : pancake.getIngredientsSet()) {
			if (ingredient.getCategory().equals("FILLING"))
				fillIngredient=true;	
		}
	}

}
