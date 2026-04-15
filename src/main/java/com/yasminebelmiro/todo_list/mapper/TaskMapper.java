package com.yasminebelmiro.todo_list.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yasminebelmiro.todo_list.dto.request.TaskRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.StatusTaskResponseDTO;
import com.yasminebelmiro.todo_list.dto.response.TaskResponseDTO;
import com.yasminebelmiro.todo_list.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    StatusTaskResponseDTO toStatus(Task task);

    @Mapping(target = "userId", source = "user.id")
    TaskResponseDTO toResponse(Task task);

    Task toEntity(TaskRequestDTO dto);

    List<TaskResponseDTO> toResponseList(List<Task> tasks);

}
