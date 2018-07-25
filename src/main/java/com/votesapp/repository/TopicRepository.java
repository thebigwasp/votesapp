package com.votesapp.repository;

import com.votesapp.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    boolean existsByName(String name);
    Topic findOneByName(String name);
}
