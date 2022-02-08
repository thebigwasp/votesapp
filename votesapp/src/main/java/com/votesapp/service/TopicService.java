package com.votesapp.service;

import com.votesapp.exception.VotesappException;
import com.votesapp.dto.TopicDto;
import com.votesapp.model.Item;
import com.votesapp.model.Topic;
import com.votesapp.model.TopicStatus;
import com.votesapp.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Value("${app-url}")
    private String appUrl;

    private TopicRepository topicRepository;
    private ItemService itemService;

    @Autowired
    public TopicService(TopicRepository topicRepository,
                        ItemService itemService){
        this.topicRepository = topicRepository;
        this.itemService = itemService;
    }

    @Transactional
    public Topic createTopic(TopicDto topicDto){
        Topic topic = new Topic();
        topic.setName(topicDto.getName());
        topic = this.topicRepository.save(topic);
        topic.setItems(this.itemService.createItems(topicDto.getItems(), topic));
        return topic;
    }

    public Topic openTopic(String topicName) throws VotesappException {
        Topic topic = topicRepository.findOneByName(topicName);
        if(topic == null){
            throw new VotesappException("Topic not found", HttpStatus.NOT_FOUND);
        }
        topic.setStatus(TopicStatus.Open);
        topic.setUrl(appUrl + "/" + topic.getName());
        this.topicRepository.save(topic);
        return topic;
    }

    public Topic closeTopic(String topicName) throws VotesappException {
        Topic topic = topicRepository.findOneByName(topicName);
        if(topic == null){
            throw new VotesappException("Topic not found", HttpStatus.NOT_FOUND);
        }
        topic.setStatus(TopicStatus.Closed);
        this.topicRepository.save(topic);
        return topic;
    }

    public TopicDto getTopicInfo(String topicName) throws VotesappException {
        Topic topic = this.topicRepository.findOneByName(topicName);
        if(topic == null){
            throw new VotesappException("Topic not found", HttpStatus.NOT_FOUND);
        }
        TopicDto topicDto = new TopicDto();
        topicDto.setName(topic.getName());
        topicDto.setUrl(topic.getUrl());
        topicDto.setStatus(topic.getStatus());
        topicDto.setItems(topic.getItems().stream()
                .map(Item::getName)
                .collect(Collectors.toList()));
        return topicDto;
    }

    public boolean existsByName(String name){
        return this.topicRepository.existsByName(name);
    }
}
