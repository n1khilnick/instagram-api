package com.geekster.instagramapi.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,scope = Post.class,property = "postId")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Timestamp createdDate;

    private Timestamp updatedDate;

    private String postData;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
