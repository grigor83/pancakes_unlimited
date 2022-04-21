package com.grigor.pancakes_unlimited;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = OrderValidator.class)
public @interface OrderConstraint {
	String message() default "Order is not valid!";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
