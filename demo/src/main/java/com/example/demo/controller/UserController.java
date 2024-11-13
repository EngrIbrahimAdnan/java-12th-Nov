package com.example.demo.controller;

import com.example.demo.bo.CreateUserRequest;
import com.example.demo.bo.UserResponse;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET endpoint to retrieve all users
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    //POST: http://localhost:8080/users/create
    //{
    //    "name": "RandomPerson",
    //    "status":"ACTIVE"
    //}
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createuser(@RequestBody CreateUserRequest request) {
        UserResponse response = userService.createUser(request);

        // Check if the response is not null (indicating a successful creation)
        if (response != null) {
            // Return a 201 Created status code along with the created user data
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            // Handle the case where the creation was not successful (e.g., validation failed)
            // You can return a different status code or error message as needed
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //POST: http://localhost:8080/users/searchUsers?filterStatus=ACTIVE
    @PostMapping("/searchUsers")
    public ResponseEntity<List<UserEntity>> filterUsers(@RequestParam(required = false, defaultValue = "none") String filterStatus) {

        List<UserEntity> filteredUsers = userService.filterUsers(filterStatus);
        // Return a 200 OK with a custom message in the response body
        if (!filteredUsers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(filteredUsers);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(null);
        }
    }



    //POST: http://localhost:8080/users/updateStatus?userId=1&newStatus=sdsd
    @PostMapping("/updateStatus")
    public ResponseEntity<UserEntity> updateUser(@RequestParam(required = false) Long userId, @RequestParam(required = false) String newStatus) {

        UserEntity modifiedUser = userService.updateUser(userId,newStatus);
        // Return a 200 OK with a custom message in the response body
        if (modifiedUser!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modifiedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(null);
        }
    }


}