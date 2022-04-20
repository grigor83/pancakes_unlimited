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
@Constraint(validatedBy = PancakeValidator.class)
public @interface PancakeConstraint {
	String message() default "ne valja !";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
