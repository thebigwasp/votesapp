package com.votesapp.validation;

import com.votesapp.validation.annotation.ItemsLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ItemsLengthValidator implements ConstraintValidator<ItemsLength, List<String>> {

    private int min;
    private int max;

    @Override
    public void initialize(ItemsLength constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<String> s, ConstraintValidatorContext constraintValidatorContext) {
        return s.stream()
                .noneMatch( itemName -> itemName.length() < this.min || itemName.length() > this.max );
    }
}
