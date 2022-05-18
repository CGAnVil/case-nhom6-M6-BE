package com.codegym.service.comment;

import com.codegym.model.CommentPost;
import com.codegym.repository.ICommentPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentPostService implements ICommentPostService{
    @Autowired
    private ICommentPostRepository commentPostRepository;

    @Override
    public Iterable<CommentPost> findAll() {
        return commentPostRepository.findAll();
    }

    @Override
    public Optional<CommentPost> findById(Long id) {
        return commentPostRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        commentPostRepository.deleteById(id);
    }

    @Override
    public CommentPost save(CommentPost commentPost) {
        return commentPostRepository.save(commentPost);
    }

    @Override
    public Iterable<CommentPost> getAllCommentByIdPost(Long idPost) {
        return commentPostRepository.getAllCommentByIdPost(idPost);
    }

    @Override
    public Iterable<CommentPost> getAllReplyComment(Long idPost, Long idComment) {
        return commentPostRepository.getAllReplyComment(idPost, idComment);
    }
}
