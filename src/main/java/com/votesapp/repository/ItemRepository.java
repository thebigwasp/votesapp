package com.votesapp.repository;

import com.votesapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * from items where name=?1 and topic_id=(select id from topics where name=?2);", nativeQuery = true)
    Item findOneByItemNameAndTopicName(String itemName, String topicName);
}
