package com.example.spring_security_with_mongodb.controller;

import java.util.List;
import java.util.Optional;

import com.example.spring_security_with_mongodb.models.User;
import com.example.spring_security_with_mongodb.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> list = userService.findAllUsers();
            return ResponseEntity.ok().body(Optional.of(list).get());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUsers(@PathVariable("username") String username) {

        try {
            User user = userService.findUserByUsername(username);
            return ResponseEntity.of(Optional.of(user));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
