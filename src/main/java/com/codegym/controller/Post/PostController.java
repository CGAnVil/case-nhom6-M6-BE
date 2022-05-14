package com.codegym.controller.Post;


import com.codegym.model.Post;
import com.codegym.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> findAllPost() {
        Iterable<Post> posts = postService.findAll();
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findByIdPost(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }


    @GetMapping("users/{id}")
    public ResponseEntity<Iterable<Post>> showAllByUser(@PathVariable("id") Long idUser) {
        Iterable<Post> posts = postService.findPostByIdUser(idUser);
        if (!posts.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@ModelAttribute Post post) {
        Post createdPost = postService.save(post);
        return new ResponseEntity<>(createdPost, HttpStatus.OK);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @ModelAttribute Post postEdit) {
        LocalDate today = LocalDate.now();
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = postOptional.get();
        post.setTitle(postEdit.getTitle());
        post.setDateCreate(today);
        post.setDescription(postEdit.getDescription());
        post.setAvatarPost(postEdit.getAvatarPost());
        post.setUser(postEdit.getUser());
        post.setStatus(postEdit.getStatus());
        post.setCategory(postEdit.getCategory());
        postService.save(postEdit);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (!optionalPost.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.removeById(id);
        return new ResponseEntity<>(optionalPost.get(), HttpStatus.OK);
    }

    @GetMapping("findStatus/{id}")
    public ResponseEntity<Iterable<Post>> findPostStatus(@PathVariable Long id) {
        Iterable<Post> posts = postService.findPostByIdStatus(id);
        if (!posts.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }



}
