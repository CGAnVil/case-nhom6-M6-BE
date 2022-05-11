package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateCreate;

    private String title;

    private String content;

    private String description;

    private String avatarPost;

    @ManyToOne
    private Category category;


    @ManyToOne
    private User user;

    @ManyToOne
    private Status status;


}
