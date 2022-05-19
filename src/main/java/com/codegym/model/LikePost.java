package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userLike;

    @ManyToOne
    private Post postLike;

    public LikePost(User userLike, Post postLike) {
        this.userLike = userLike;
        this.postLike = postLike;
    }
}
