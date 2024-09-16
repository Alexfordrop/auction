package com.petproject.auction.service.impl;

import com.petproject.auction.domain.entity.User;
import com.petproject.auction.domain.repository.UserRepository;
import com.petproject.auction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, User user) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Лот с id " + id + " не найден");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Пользователь с id " + id + " не найден"));
    }

    @Override
    public void deleteUser(UUID id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Пользователь с id " + id + " не найден");
        }
        userRepository.deleteById(id);
    }
}
