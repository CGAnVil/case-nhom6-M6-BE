package com.codegym.repository;

import com.codegym.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IPostRepository extends JpaRepository<Post , Long> {

    Iterable <Post> findAllByStatus_Id (Long statusId);

    Iterable <Post> findAllByUser_Id (Long idUser);


    @Modifying
    @Query(value = "update posts set posts.state_id = 2 where posts.id = ?1", nativeQuery = true)
    void blockPost(Long post_id);

    @Modifying
    @Query(value = "update posts set posts.state_id = 1 where posts.id = ?1", nativeQuery = true)
    void unblockPost(Long post_id);
}
