package com.codegym.repository;

import com.codegym.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IPostRepository extends JpaRepository<Post , Long> {

    Iterable <Post> findAllByStatus_Id (Long statusId);

    Iterable <Post> findAllByUser_Id (Long idUser);




}
