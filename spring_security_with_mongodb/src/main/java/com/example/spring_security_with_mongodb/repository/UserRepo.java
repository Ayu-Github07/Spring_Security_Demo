package com.example.spring_security_with_mongodb.repository;

import com.example.spring_security_with_mongodb.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    public User findByUsername(String username);
}
