package com.book_catalog.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<ValidPrice, Double> {

    @Override
    public void initialize(ValidPrice constraintAnnotation) {
        // Initialization logic if needed
    }

    @Override
    public boolean isValid(Double price, ConstraintValidatorContext context) {
        if (price == null) {
            return true; // Ignore validation if price is null
        }
        return price >= 1.0; // Apply validation if price is not null
    }
}
