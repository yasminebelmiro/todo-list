package com.yasminebelmiro.todo_list.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yasminebelmiro.todo_list.dto.request.TaskRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.StatusTaskResponseDTO;
import com.yasminebelmiro.todo_list.dto.response.TaskResponseDTO;
import com.yasminebelmiro.todo_list.entity.Task;
import com.yasminebelmiro.todo_list.entity.TaskList;
import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.mapper.TaskMapper;
import com.yasminebelmiro.todo_list.repository.TaskListRepository;
import com.yasminebelmiro.todo_list.repository.TaskRepository;
import com.yasminebelmiro.todo_list.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    private static final Logger logger = Logger.getLogger(TaskService.class.getName());
    private final TaskRepository todoRepository;
    private final UserRepository userRepository;
    private final TaskListRepository taskListRepository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository todoRepository, UserRepository userRepository,
            TaskListRepository taskListRepository, TaskMapper mapper) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.taskListRepository = taskListRepository;
        this.mapper = mapper;
    }

    public TaskResponseDTO create(TaskRequestDTO dto, Long userId) {
        logger.info("Criando todo para o usuário: " + userId);
        User user = userRepository.findByIdOrThrow(userId);
        Task task = mapper.toEntity(dto);
        TaskList taskList = taskListRepository.findByIdOrThrow(dto.taskListId());
        task.setUser(user);
        task.setTaskList(taskList);
        Task savedTask = todoRepository.save(task);
        return mapper.toResponse(savedTask);
    }

    public List<TaskResponseDTO> listByUserIdOrdenedByPrioridade(Long userId) {
        logger.info("Listando to-do's ordenadas por prioridade");
        Sort sort = Sort.by("priority").descending();
        List<Task> tasks = todoRepository.findByUserId(userId, sort);
        return mapper.toResponseList(tasks);
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {

        logger.info("Atualizando to-do com id: " + id);
        todoRepository.findByIdOrThrow(id);
        Task updatedTask = mapper.toEntity(dto);
        updatedTask.setId(id);
        Task savedTask = todoRepository.save(updatedTask);
        return mapper.toResponse(savedTask);
    }

    public void delete(Long id) {
        logger.info("Deletando to-do com id: " + id);
        Task todoToDelete = todoRepository.findByIdOrThrow(id);
        todoRepository.delete(todoToDelete);
    }

    @Transactional
    public StatusTaskResponseDTO atualizarStatus(Long id) {
        logger.info("Atualizando status do to-do com id: " + id);
        Task todoToUpdate = todoRepository.findByIdOrThrow(id);
        todoToUpdate.setCompleted(!todoToUpdate.isCompleted());
        return mapper.toStatus(todoToUpdate);
    }

}
