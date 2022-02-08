package com.votesapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicStatus status = TopicStatus.Closed;

    @Column(unique = true, nullable = false)
    @Length(min = 1, max = 255)
    private String name;

    @Column(length = 500)
    private String url;

    @OneToMany(mappedBy = "topic")
    private List<Item> items = new ArrayList<>();
}
