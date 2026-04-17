package com.yasminebelmiro.todo_list.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.yasminebelmiro.todo_list.dto.request.TaskListRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.TaskListResponseDTO;
import com.yasminebelmiro.todo_list.entity.TaskList;
import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.mapper.TaskListMapper;
import com.yasminebelmiro.todo_list.repository.TaskListRepository;
import com.yasminebelmiro.todo_list.repository.UserRepository;

@Service
public class TaskListService {
    private static final Logger logger = Logger.getLogger(TaskListService.class.getName());
    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;
    private final TaskListMapper mapper;

    public TaskListService(TaskListRepository taskListRepository, UserRepository userRepository,
            TaskListMapper mapper) {
        this.taskListRepository = taskListRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public TaskListResponseDTO create(TaskListRequestDTO dto, Long userId) {
        logger.info("Criando task list para o usuário: " + userId);
        User user = userRepository.findByIdOrThrow(userId);
        TaskList taskList = mapper.toEntity(dto);
        taskList.setUser(user);
        TaskList savedTaskList = taskListRepository.save(taskList);
        return mapper.toResponse(savedTaskList);
    }

    public List<TaskListResponseDTO> listByUserId(Long userId) {
        logger.info("Listando task lists para o usuário: " + userId);
        List<TaskList> taskLists = taskListRepository.findByUserId(userId);
        return mapper.toResponseList(taskLists);
    }

    public TaskListResponseDTO getById(Long id) {
        logger.info("Obtendo task list com id: " + id);
        TaskList taskList = taskListRepository.findByIdOrThrow(id);
        return mapper.toResponse(taskList);
    }

    public TaskListResponseDTO update(Long id, TaskListRequestDTO dto) {
        logger.info("Atualizando task list com id: " + id);
        taskListRepository.findByIdOrThrow(id);
        TaskList updatedTaskList = mapper.toEntity(dto);
        updatedTaskList.setId(id);
        TaskList savedTaskList = taskListRepository.save(updatedTaskList);
        return mapper.toResponse(savedTaskList);
    }

    public void delete(Long id) {
        logger.info("Deletando task list com id: " + id);
        taskListRepository.findByIdOrThrow(id);
        taskListRepository.deleteById(id);
    }
}
