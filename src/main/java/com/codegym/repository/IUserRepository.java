package com.codegym.repository;


import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Iterable<User> findUsersByUserNameContaining(String user_name);

    @Modifying
    @Query(value = "update user set user.status_id = 2 where user.id = ?1", nativeQuery = true)
    void blockUser(Long user_id);

    Optional<User> findByUserName(String user_name);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String user_name);

    Optional<User> findByFullName(String fullName);

    Optional<User> findByEmail(String email);



}
