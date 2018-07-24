package com.votesapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "votes")
@Getter
@Setter
@NoArgsConstructor
public class Votes {

    @EmbeddedId
    private VoteId id;
}
