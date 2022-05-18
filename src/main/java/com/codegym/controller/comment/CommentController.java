package com.codegym.controller.comment;

import com.codegym.model.CommentPost;
import com.codegym.service.comment.ICommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentPostService commentPostService;

    @GetMapping
    public ResponseEntity<Iterable<CommentPost>> findAll() {
        Iterable<CommentPost> commentPosts = commentPostService.findAll();
        return new ResponseEntity<>(commentPosts, HttpStatus.OK);
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<Iterable<CommentPost>> getAllCommentByPostId(@PathVariable Long idPost) {
        Iterable<CommentPost> commentPosts = commentPostService.getAllCommentByIdPost(idPost);
        return new ResponseEntity<>(commentPosts, HttpStatus.OK);
    }

    @GetMapping("/{idPost}/{idComment}")
    public ResponseEntity<Iterable<CommentPost>> getAllReplyComment(@PathVariable Long idPost, @PathVariable Long idComment) {
        Iterable<CommentPost> commentPosts = commentPostService.getAllReplyComment(idPost, idComment);
        return new ResponseEntity<>(commentPosts, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CommentPost> saveCommentPost(@RequestBody CommentPost commentPost) {
        return new ResponseEntity<>(commentPostService.save(commentPost), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<CommentPost> updateCommentPost(@PathVariable Long id, @RequestBody CommentPost newCommentPost) {
        Optional<CommentPost> commentPostOptional = commentPostService.findById(id);
        if (!commentPostOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        newCommentPost.setId(id);
        return new ResponseEntity<>(commentPostService.save(newCommentPost), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentPost> deleteCommentPost(@PathVariable Long id) {
        Optional<CommentPost> commentPostOptional = commentPostService.findById(id);
        if (!commentPostOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentPostService.removeById(id);
        return new ResponseEntity<>(commentPostOptional.get(), HttpStatus.OK);
    }
}
