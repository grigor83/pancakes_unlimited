package com.grigor.pancakes_unlimited;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryValidator implements ConstraintValidator<CategoryConstraint, CharSequence>  {
    private List<String> acceptedValues;

	@Override
    public void initialize(CategoryConstraint annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (value == null) {
            return false;
        }

        return acceptedValues.contains(value.toString());
	}

}
