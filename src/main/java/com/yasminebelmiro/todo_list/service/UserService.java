package com.yasminebelmiro.todo_list.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yasminebelmiro.todo_list.dto.request.UserRequestDTO;
import com.yasminebelmiro.todo_list.dto.response.UserResponseDTO;
import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.mapper.UserMapper;
import com.yasminebelmiro.todo_list.repository.UserRepository;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO create(UserRequestDTO dto) {
        logger.info("Criando usuário: " + dto.name());
        User user = mapper.toEntity(dto);
        String encodedPassword = passwordEncoder.encode(dto.password()); 
        user.setPassword(encodedPassword);
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

    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        logger.info("Atualizando usuário com id: " + id);
        User user = userRepository.findByIdOrThrow(id);
        String encodedPassword = passwordEncoder.encode(dto.password()); 
        user.setPassword(encodedPassword);
        User existingUser = mapper.toEntity(dto);
        existingUser.setId(id);
        User updatedUser = userRepository.save(existingUser);
        return mapper.toResponse(updatedUser);
    }

    public void delete(Long id) {
        logger.info("Deletando usuário com id: " + id);
        userRepository.deleteById(id);
    }
}
