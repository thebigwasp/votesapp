package com.votesapp.validation;

import com.votesapp.service.TopicService;
import com.votesapp.validation.annotation.UniqueTopicName;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

public class UniqueTopicNameValidator implements ConstraintValidator<UniqueTopicName, String> {

    private TopicService topicService;

    @Autowired
    public UniqueTopicNameValidator(TopicService topicService){
        this.topicService = topicService;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !this.topicService.existsByName(s);
    }
}
