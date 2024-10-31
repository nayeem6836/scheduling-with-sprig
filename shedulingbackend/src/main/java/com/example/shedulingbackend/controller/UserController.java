package com.example.shedulingbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.shedulingbackend.dtos.UserDto;
import com.example.shedulingbackend.model.Availability;
import com.example.shedulingbackend.model.User;
import com.example.shedulingbackend.repository.AvailabilityRepository;
import com.example.shedulingbackend.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from your frontend
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AvailabilityRepository availabilityRepository;

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.registerUser(userDto.getEmail(), userDto.getPassword(), userDto.getRole());
            return ResponseEntity.status(201).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to register user: " + e.getMessage());
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDto userDto) {
        try {
            User user = userService.loginUser(userDto.getEmail(), userDto.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/availability/add")
    public ResponseEntity<?> addAvailability(@RequestBody Availability availability) {
        availabilityRepository.save(availability);
        return ResponseEntity.status(201).body(availability);
    }

    @GetMapping("/availability/{user}")
    public ResponseEntity<List<Availability>> getAvailability(@PathVariable String user) {
        List<Availability> availabilityList = availabilityRepository.findByUser(user);
        return ResponseEntity.ok(availabilityList);
    }

    @PutMapping("/availability/update/{id}")
    public ResponseEntity<?> updateAvailability(@PathVariable String id, @RequestBody Availability updatedSlot) {
        Availability existingSlot = availabilityRepository.findById(id).orElse(null);
        if (existingSlot == null) {
            return ResponseEntity.status(404).body("Availability slot not found");
        }
        existingSlot.setStart(updatedSlot.getStart());
        existingSlot.setEnd(updatedSlot.getEnd());
        existingSlot.setDuration(updatedSlot.getDuration());
        availabilityRepository.save(existingSlot);
        return ResponseEntity.ok(existingSlot);
    }

    @DeleteMapping("/availability/delete/{id}")
    public ResponseEntity<?> deleteAvailability(@PathVariable String id) {
        availabilityRepository.deleteById(id);
        return ResponseEntity.ok("Availability slot deleted");
    }
}
