package com.votesapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class VoteId implements Serializable {

    @Column(length = 500)
    private String user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (!(o instanceof VoteId)) return false;
        VoteId that = (VoteId) o;
        return this.user.equals(that.user) && this.topic.getId().equals(that.topic.getId());
    }
}
