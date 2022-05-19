package com.codegym.repository;

import com.codegym.model.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikePostRepository extends JpaRepository<LikePost, Long> {
    @Query(value = "select count(like_post.id) from like_post where post_like_id = ?1", nativeQuery = true)
    Integer countLikeByIdPost(Long idPost);

    @Query(value = "select * from like_post where post_like_id = ?1 and user_like_id = ?2", nativeQuery = true)
    Optional<LikePost> findLikePostByIdPostAndIdUser(Long idPost, Long idUser);
}
