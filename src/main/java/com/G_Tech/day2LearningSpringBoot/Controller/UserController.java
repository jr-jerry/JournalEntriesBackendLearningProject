package com.G_Tech.day2LearningSpringBoot.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
   

    // Get a user by ID
    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserById(@PathVariable String userName) {
        Optional<User> userOptional = userServices.getUserByUsername(userName);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    // Update a user by ID
    @PutMapping()
    public ResponseEntity<User> updateUser( @RequestBody User userDetails) {
        Authentication authenticationHolder=SecurityContextHolder.getContext().getAuthentication();
        String username=authenticationHolder.getName();
        //9651347494
        //8697141880
        //9389564457
        User updatedUser = userServices.putService(userDetails, username);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


}