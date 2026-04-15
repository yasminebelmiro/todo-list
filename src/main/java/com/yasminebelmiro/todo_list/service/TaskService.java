package com.yasminebelmiro.todo_list.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yasminebelmiro.todo_list.dto.response.StatusTaskResponseDTO;
import com.yasminebelmiro.todo_list.dto.response.TaskResponseDTO;
import com.yasminebelmiro.todo_list.entity.Task;
import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.mapper.TaskMapper;
import com.yasminebelmiro.todo_list.repository.TaskRepository;
import com.yasminebelmiro.todo_list.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    private static final Logger logger = Logger.getLogger(TaskService.class.getName());
    private final TaskRepository todoRepository;
    private final UserRepository userRepository;
    private final TaskMapper mapper;

    public TaskService(TaskRepository todoRepository, UserRepository userRepository, TaskMapper mapper) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public TaskResponseDTO create(Task todo, Long userId) {
        logger.info("Criando todo para o usuário: " + userId);
        User user = userRepository.findByIdOrThrow(userId);
        todo.setUser(user);
        Task savedTask = todoRepository.save(todo);
        return mapper.toResponse(savedTask);
    }

    public List<TaskResponseDTO>listByUserIdOrdenedByPrioridade(Long userId) {
        logger.info("Listando to-do's ordenadas por prioridade");
        Sort sort = Sort.by("prioridade").ascending().and(Sort.by("nome").ascending());
        List<Task> tasks = todoRepository.findByUserId(userId, sort);
        return mapper.toResponseList(tasks);
    }

    public TaskResponseDTO update(Long id, Task todo) {

        logger.info("Atualizando to-do com id: " + id);
        Task updatedTask = todoRepository.findByIdOrThrow(id);

        updatedTask.setNome(todo.getNome());
        updatedTask.setDescricao(todo.getDescricao());
        updatedTask.setPrioridade(todo.getPrioridade());
        updatedTask.setRealizada(todo.isRealizada());
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
        todoToUpdate.setRealizada(!todoToUpdate.isRealizada());
        return mapper.toStatus(todoToUpdate);
    }

}
