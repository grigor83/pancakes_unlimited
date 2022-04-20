package com.grigor.pancakes_unlimited;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientValidator implements ConstraintValidator<IngredientConstraint, Ingredient> {
	@Autowired
	IngredientRepo iRepo;
	

	@Override
	public boolean isValid(Ingredient ingredient, ConstraintValidatorContext context) {
		String name = ingredient.getName();

		Optional<Ingredient> i = iRepo.findByName(name);
	    if (i.isPresent())
	        return false;
				
		return true;
	}

}
