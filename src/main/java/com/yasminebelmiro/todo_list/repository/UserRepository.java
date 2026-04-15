package com.yasminebelmiro.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.exception.UserNotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
