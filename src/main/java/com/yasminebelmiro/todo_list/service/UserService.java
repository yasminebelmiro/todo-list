package com.yasminebelmiro.todo_list.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.yasminebelmiro.todo_list.dto.response.UserResponseDTO;
import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.mapper.UserMapper;
import com.yasminebelmiro.todo_list.repository.UserRepository;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public UserResponseDTO create(User user) {
        logger.info("Criando usuário: " + user.getName());
        User savedUser = userRepository.save(user);
        return mapper.toResponse(savedUser);
    }

    public List<UserResponseDTO> getAll() {
        logger.info("Listando todos os usuários");
        List<User> users = userRepository.findAll();
        return mapper.toResponseList(users);
    }

    public UserResponseDTO getById(Long id) {
        logger.info("Buscando usuário com id: " + id);
        User user = userRepository.findByIdOrThrow(id);
        return mapper.toResponse(user);
    }

    public UserResponseDTO update(Long id, User user) {
        logger.info("Atualizando usuário com id: " + id);
        User existingUser = userRepository.findByIdOrThrow(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return mapper.toResponse(updatedUser);
    }

    public void delete(Long id) {
        logger.info("Deletando usuário com id: " + id);
        userRepository.deleteById(id);
    }
}
