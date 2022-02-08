package com.votesapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.votesapp.model.TopicStatus;
import com.votesapp.validation.annotation.ItemsLength;
import com.votesapp.validation.annotation.NoDuplicateItems;
import com.votesapp.validation.annotation.UniqueTopicName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.UniqueConstraint;
import java.util.List;

@Getter
@Setter
public class TopicDto {

    private TopicStatus status;
    @UniqueTopicName
    @Length(min = 1, max = 255)
    private String name;
    private String url;
    @NoDuplicateItems
    @ItemsLength(min = 1, max = 255)
    private List<String> items;
}
