package com.example.demo.service;

import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setStatus(request.getStatus().toString());

        userEntity = userRepository.save(userEntity);
        UserResponse response = new UserResponse(userEntity.getId(), userEntity.getName(), userEntity.getStatus());
        return response;
    }

    @Override
    public List<UserEntity> filterUsers(String filterStatus) {
        List<UserEntity> allUsers = userRepository.findAll();
        List<UserEntity> filteredUsers = new ArrayList();

        for (UserEntity user : allUsers) {
            String currentStatus = user.getStatus();

            // Check if currentStatus is not null before calling equals
            if (currentStatus != null && currentStatus.equals(filterStatus)) {
                filteredUsers.add(user);
            }
        }
        // Return an empty list if no users match the filter
        return filteredUsers;
    }


    @Override
    public UserEntity updateUser(Long userId, String newStatus) {
        List<UserEntity> allUsers = userRepository.findAll();

        for (UserEntity user : allUsers) {
            if (user.getId().equals(userId)) {
                user.setStatus(newStatus);
                return user;
            }
        }
        return null;
    }
}