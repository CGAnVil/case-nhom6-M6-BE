package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreate = LocalDate.now();


    private LocalDate dateLastFix;

    @Column(columnDefinition="LONGTEXT")

    private String title;

    @Column(columnDefinition="LONGTEXT")
    private String content;


    private String description;

    private String avatarPost;

    @ManyToOne
    private Category category;


    @ManyToOne
    private User user;

    @ManyToOne
    private Status status;

    @ManyToOne
    private PostState state;
    public Post(LocalDate dateLastFix, String title, String content, String description, String avatarPost, Category category, User user, Status status, PostState state) {
        this.dateLastFix = dateLastFix;
        this.title = title;
        this.content = content;
        this.description = description;
        this.avatarPost = avatarPost;
        this.category = category;
        this.user = user;
        this.status = status;
        this.state = state;
    }
}
