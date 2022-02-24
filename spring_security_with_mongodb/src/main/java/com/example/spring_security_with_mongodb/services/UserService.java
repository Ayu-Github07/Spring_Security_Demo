package com.example.spring_security_with_mongodb.services;

import java.util.List;

import com.example.spring_security_with_mongodb.models.User;
import com.example.spring_security_with_mongodb.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> findAllUsers() {
        return this.userRepo.findAll();
    }

    public User findUserByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }

    public void setUsers(User user) {

        this.userRepo.save(user);
    }
}
