package com.grigor.pancakes_unlimited;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Constraint(validatedBy = CategoryValidator.class)

public @interface CategoryConstraint {
	 Class<? extends Enum<?>> enumClass();
	 String message() default "Invalid category name";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};

}
