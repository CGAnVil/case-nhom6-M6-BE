package com.codegym.service.UserStatus;

import com.codegym.model.UserStatus;
import com.codegym.repository.IUserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserStatusService implements IUserStatusService{

    @Autowired
    private IUserStatusRepository userStatusRepository;
    @Override
    public Iterable<UserStatus> findAll() {
        return userStatusRepository.findAll();
    }

    @Override
    public Optional<UserStatus> findById(Long id) {
        return userStatusRepository.findById(id);
    }

    @Override
    public void removeById(Long id) {
        userStatusRepository.deleteById(id);
    }

    @Override
    public UserStatus save(UserStatus userStatus) {
        return userStatusRepository.save(userStatus);
    }


}
