package com.yasminebelmiro.todo_list.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yasminebelmiro.todo_list.dto.request.UserRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.UserResponseDTO;
import com.yasminebelmiro.todo_list.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponse(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDTO dto);

    List<UserResponseDTO> toResponseList(List<User> users);
}
