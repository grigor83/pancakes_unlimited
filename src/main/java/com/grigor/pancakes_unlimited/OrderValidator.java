package com.grigor.pancakes_unlimited;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderValidator implements ConstraintValidator<OrderConstraint, Order> {
	@Autowired
	PancakeRepo pRepo;

	@Override
	public boolean isValid(Order order, ConstraintValidatorContext context) {
		if (order.getPancakes()==null)
			return false;
				
		Optional<Pancake> p;
		for (Pancake pancake : order.getPancakes()) {
			p= pRepo.findById(pancake.getId());			
			if(!p.isPresent())
				return false;
		}
		
		LocalDateTime localDateTime = LocalDateTime.now();
		order.setTime(localDateTime);
		
		return true;
	}

}
