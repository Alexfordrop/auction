package com.petproject.auction.rest.controller;

import com.petproject.auction.client.UserApi;
import com.petproject.auction.model.UserDTO;
import com.petproject.auction.rest.mapper.UserMapper;
import com.petproject.auction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        return ResponseEntity.ok(userMapper.toDTO(userService.createUser(userMapper.toEntity(userDTO))));
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userMapper.toDTOList(userService.getAllUser()));
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(UUID id) {
        return ResponseEntity.ok(userMapper.toDTO(userService.getUserById(id)));
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UUID id, UserDTO userDTO) {
        return ResponseEntity.ok(userMapper.toDTO(userService.updateUser(id, userMapper.toEntity(userDTO))));
    }
}
