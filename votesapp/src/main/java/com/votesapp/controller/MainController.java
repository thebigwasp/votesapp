package com.votesapp.controller;

import com.votesapp.dto.TopicDto;
import com.votesapp.exception.VotesappException;
import com.votesapp.service.ItemService;
import com.votesapp.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
public class MainController {

    private TopicService topicService;
    private ItemService itemService;

    @Autowired
    public MainController(TopicService topicService,
                          ItemService itemService){
        this.topicService = topicService;
        this.itemService = itemService;
    }

    @PostMapping("/topic/create")
    public ResponseEntity create(@RequestBody @Valid TopicDto topicDto){ // 1
        topicService.createTopic(topicDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/topic/{topicName}/open")
    public ResponseEntity openTopic(@PathVariable String topicName) throws VotesappException { // 2
        topicService.openTopic(topicName);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/topic/{topicName}/close")
    public ResponseEntity closeTopic(@PathVariable String topicName) throws VotesappException { // 3
        topicService.closeTopic(topicName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/topic/{topicName}/{itemName}")
    public ResponseEntity showItemStatistics(@PathVariable String topicName,
                                             @PathVariable String itemName) throws VotesappException { // 4
        return ResponseEntity.ok(this.itemService.getItemInfo(topicName, itemName));
    }

    @GetMapping("/topic/{topicName}")
    public ResponseEntity showTopicStatistics(@PathVariable String topicName) throws VotesappException { // 5
        return ResponseEntity.ok(this.topicService.getTopicInfo(topicName));
    }

    @PatchMapping("/topic/{topicName}/{itemName}/vote")
    public ResponseEntity vote(@PathVariable String topicName,
                               @PathVariable String itemName,
                               HttpServletRequest request) throws VotesappException { // 6
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ResponseEntity.ok(this.itemService.vote(topicName, itemName, request.getHeader("user-agent"), ipAddress));
    }
}
