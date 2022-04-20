package com.grigor.pancakes_unlimited;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = IngredientValidator.class)
public @interface IngredientConstraint {
	String message() default "Name of ingredient must be unique!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
