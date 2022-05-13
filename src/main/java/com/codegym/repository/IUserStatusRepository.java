package com.codegym.repository;

import com.codegym.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserStatusRepository extends JpaRepository<UserStatus, Long> {

}
