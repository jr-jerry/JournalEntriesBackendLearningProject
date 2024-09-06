package com.G_Tech.day2LearningSpringBoot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.G_Tech.day2LearningSpringBoot.entity.User;
import com.G_Tech.day2LearningSpringBoot.services.userServices;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    userServices userServices;
    @GetMapping("/health-check")
    public String controllerEndPoint(){
        return "Status ";

    }
     @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userServices.saveNewUser(user); // Assuming null is acceptable for the ID when creating a new user
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
