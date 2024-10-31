package com.example.shedulingbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shedulingbackend.model.Availability;

import java.util.List;

public interface AvailabilityRepository extends MongoRepository<Availability, String> {
    List<Availability> findByUser(String user);
}
