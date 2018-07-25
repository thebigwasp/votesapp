package com.votesapp.validation.annotation;

import com.votesapp.validation.UniqueTopicNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTopicNameValidator.class)
@Documented
public @interface UniqueTopicName {

    String message() default "Such topic is already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
