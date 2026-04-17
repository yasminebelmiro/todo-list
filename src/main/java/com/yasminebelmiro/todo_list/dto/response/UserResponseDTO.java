package com.yasminebelmiro.todo_list.dto.response;

import com.yasminebelmiro.todo_list.entity.User;

public record UserResponseDTO(Long id, String name, String email) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail()

        );
    }
}
