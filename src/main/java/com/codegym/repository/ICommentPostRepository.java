package com.codegym.repository;

import com.codegym.model.CommentPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ICommentPostRepository extends JpaRepository<CommentPost, Long> {
    @Query(value = "select * from comment_post where post_comment_post_id = ?1", nativeQuery = true)
    Iterable<CommentPost> getAllCommentByIdPost(Long idPost);

    @Query(value = "select * from comment_post where comment_post.post_comment_post_id = ?1 and comment_post.comment_post_id = ?2", nativeQuery = true)
    Iterable<CommentPost> getAllReplyComment(Long idPost, Long idComment);
}
