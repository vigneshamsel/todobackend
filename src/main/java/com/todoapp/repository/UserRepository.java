package com.todoapp.repository;

import com.todoapp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByname(String userName);

    boolean existsByname(String accountNo);
}