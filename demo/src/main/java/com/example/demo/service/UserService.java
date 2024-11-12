package com.example.demo.service;
import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.UserEntity;

import java.util.List;
public interface UserService {
    List<UserEntity> getAllUsers();
    // You can define other service methods here
    UserResponse createUser (CreateUserRequest request);
}