package com.votesapp.service;

import com.votesapp.dto.ItemDto;
import com.votesapp.exception.VotesappException;
import com.votesapp.model.Item;
import com.votesapp.model.Topic;
import com.votesapp.model.Vote;
import com.votesapp.model.VoteId;
import com.votesapp.repository.ItemRepository;
import com.votesapp.repository.TopicRepository;
import com.votesapp.repository.VoteRepository;
import org.hibernate.criterion.Example;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.predicate.BetweenPredicate;
import org.hibernate.query.criteria.internal.predicate.ExistsPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;
    private TopicRepository topicRepository;
    private VoteRepository voteRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository,
                       TopicRepository topicRepository,
                       VoteRepository voteRepository){
        this.itemRepository = itemRepository;
        this.topicRepository = topicRepository;
        this.voteRepository = voteRepository;
    }

    public List<Item> createItems(List<String> names, Topic topic){
        List<Item> items = new ArrayList<>();
        names.forEach( (name) -> {
            Item item = new Item();
            item.setName(name);
            item.setTopic(topic);
            items.add(item);
        });
        return itemRepository.saveAll(items);
    }

    public ItemDto getItemInfo(String topicName, String itemName) throws VotesappException {
        Item item = this.itemRepository.findOneByItemNameAndTopicName(itemName, topicName);
        if(item == null){
            throw new VotesappException("Item not found", HttpStatus.NOT_FOUND);
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setName(item.getName());
        itemDto.setVotes(item.getVotes());
        return itemDto;
    }

    @Transactional
    public ItemDto vote(String topicName, String itemName, String user) throws VotesappException {
        Vote vote = this.voteRepository.findOneByUserAndTopicName(user, topicName);
        if(vote != null){
            throw new VotesappException("Already voted", HttpStatus.FORBIDDEN);
        }
        Item item = this.itemRepository.findOneByItemNameAndTopicName(itemName, topicName);
        if(item == null){
            throw new VotesappException("Item not found", HttpStatus.NOT_FOUND);
        }
        VoteId voteId = new VoteId();
        voteId.setUser(user);
        voteId.setTopic(item.getTopic());
        vote = new Vote();
        vote.setId(voteId);
        this.voteRepository.save(vote);
        item.setVotes(item.getVotes() + 1);
        this.itemRepository.save(item);
        ItemDto itemDto = new ItemDto();
        itemDto.setName(item.getName());
        itemDto.setVotes(item.getVotes());
        return itemDto;
    }
}
