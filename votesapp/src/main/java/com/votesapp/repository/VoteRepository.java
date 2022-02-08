package com.votesapp.repository;

import com.votesapp.model.Topic;
import com.votesapp.model.Vote;
import com.votesapp.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, VoteId> {

    @Query(value = "select * from votes where user=?1 and topic_id=(select id from topics where name=?2);", nativeQuery = true)
    Vote findOneByUserAndTopicName(String user, String topicName);
}
