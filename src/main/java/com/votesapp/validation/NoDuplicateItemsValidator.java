package com.votesapp.validation;

import com.votesapp.validation.annotation.NoDuplicateItems;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NoDuplicateItemsValidator implements ConstraintValidator<NoDuplicateItems, List<String>> {

    @Override
    public boolean isValid(List<String> s, ConstraintValidatorContext constraintValidatorContext) {
        Set<String> set = new HashSet<>(s);
        return s.size() == set.size();
    }
}
