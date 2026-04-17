package com.yasminebelmiro.todo_list.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yasminebelmiro.todo_list.dto.request.TaskListRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.TaskListResponseDTO;
import com.yasminebelmiro.todo_list.entity.TaskList;

@Mapper(componentModel = "spring")
public interface  TaskListMapper{

    @Mapping(target = "userId", source = "user.id")
    TaskListResponseDTO toResponse(TaskList taskList);

    TaskList toEntity(TaskListRequestDTO dto);

    List<TaskListResponseDTO> toResponseList(List<TaskList> taskLists);


    
}
