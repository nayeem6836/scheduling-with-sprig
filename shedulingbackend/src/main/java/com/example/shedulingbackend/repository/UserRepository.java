package com.example.shedulingbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shedulingbackend.model.User;

public interface  UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);
    
}
