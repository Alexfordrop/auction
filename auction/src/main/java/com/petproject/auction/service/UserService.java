package com.petproject.auction.service;

import com.petproject.auction.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAllUser();

    User createUser(User user);

    User updateUser(UUID id, User user);

    User getUserById(UUID id);

    void deleteUser(UUID id);
}
