package com.codegym.service.Account.user;


import com.codegym.model.User;
import com.codegym.model.UserStatus;
import com.codegym.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Iterable<User> findUsersByUserNameContaining(String user_name) {
        return userRepository.findUsersByUserNameContaining(user_name);
    }

    @Override
    public Optional<User> findByUsername(String user_name) {
        return userRepository.findByUserName(user_name);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String user_name) {
        return userRepository.existsByUserName(user_name);
    }

    @Override
    public Optional<User> findByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
