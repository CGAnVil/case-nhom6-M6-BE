package com.codegym.repository;


import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Iterable<User> findUsersByUserNameContaining(String user_name);

    Optional<User> findByUserName(String user_name);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String user_name);

    Optional<User> findByFullName(String fullName);

    Optional<User> findByEmail(String email);

}
