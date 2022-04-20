package com.grigor.pancakes_unlimited;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PancakeValidator implements ConstraintValidator<PancakeConstraint, Pancake> {
	@Autowired
	IngredientRepo iRepo;

	@Override
	public boolean isValid(Pancake p, ConstraintValidatorContext context) {
		if (p.getBasicIngredient()==null)
			return false;
		
		Optional<Ingredient> i = iRepo.findById(p.getBasicIngredient().getId());
	    if (!i.isPresent())
	        return false;
	    
	    Pancake pancake=new Pancake(i.get());	    
	    if (p.getIngredientsSet() != null) {
	    	for (Ingredient ingredient : p.getIngredientsSet()) {
		    	i = iRepo.findById(ingredient.getId());
		    	if (i.isPresent())
		    		pancake.getIngredientsSet().add(i.get());
			}
	    }
	    p.setBasicIngredient(pancake.getBasicIngredient());
	    p.setIngredientsSet(pancake.getIngredientsSet());	    
		return true;
	}
	

}
