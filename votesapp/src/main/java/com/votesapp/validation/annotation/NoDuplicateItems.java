package com.votesapp.validation.annotation;

import com.votesapp.validation.NoDuplicateItemsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoDuplicateItemsValidator.class)
@Documented
public @interface NoDuplicateItems {

    String message() default "All items must be different";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
