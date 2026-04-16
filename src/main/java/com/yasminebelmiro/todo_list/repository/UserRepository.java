package com.yasminebelmiro.todo_list.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.exception.UserNotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    default Optional<User> findByEmail(String email) {
        return Optional.ofNullable(findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Utilizador não encontrado com o email: " + email)));
    };
}
