package com.votesapp.validation.annotation;

import com.votesapp.validation.ItemsLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemsLengthValidator.class)
@Documented
public @interface ItemsLength {

    int min() default 0;

    int max() default 2147483647;

    String message() default "items length must be between {min} and {max}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
