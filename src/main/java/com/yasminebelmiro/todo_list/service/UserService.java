package com.yasminebelmiro.todo_list.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.yasminebelmiro.todo_list.entity.User;
import com.yasminebelmiro.todo_list.repository.UserRepository;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        logger.info("Criando usuário: " + user.getName());
        return userRepository.save(user);
    }

    public List<User> getAll() {
        logger.info("Listando todos os usuários");
        return userRepository.findAll();
    }

    public User getById(Long id) {
        logger.info("Buscando usuário com id: " + id);

        return userRepository.findByIdOrThrow(id);
    }

    public User update(Long id, User user) {
        logger.info("Atualizando usuário com id: " + id);
        User existingUser = userRepository.findByIdOrThrow(id);
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        logger.info("Deletando usuário com id: " + id);
        userRepository.deleteById(id);
    }
}
