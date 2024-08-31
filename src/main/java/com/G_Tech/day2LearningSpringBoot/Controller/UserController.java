package com.G_Tech.day2LearningSpringBoot.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.G_Tech.day2LearningSpringBoot.entity.User;
import com.G_Tech.day2LearningSpringBoot.services.userServices;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private  userServices userServices;

    // Get all users
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServices.getService();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Create a new user
    @PostMapping("/{createUser}")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userServices.postService(user, null); // Assuming null is acceptable for the ID when creating a new user
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    // Get a user by ID
    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserById(@PathVariable String userName) {
        Optional<User> userOptional = userServices.getIdService(userName);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    // Update a user by ID
    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUser(@PathVariable String userName, @RequestBody User userDetails) {
        User updatedUser = userServices.putService(userDetails, userName);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


}