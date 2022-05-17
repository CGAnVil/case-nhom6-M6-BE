package com.codegym.service.Account.user;

import com.codegym.model.User;
import com.codegym.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    Iterable<User> findUsersByUserNameContaining(String user_name);

    void blockUser(Long user_id);

    void unBlockUser(Long user_id);

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Optional<User> findByFullName(String fullName);

    Optional<User> findByEmail(String email);

}
