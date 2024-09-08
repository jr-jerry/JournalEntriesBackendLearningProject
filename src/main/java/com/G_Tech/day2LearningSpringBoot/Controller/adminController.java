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
import java.util.List;


import com.G_Tech.day2LearningSpringBoot.services.userServices;

@RestController
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private userServices userServicesProvider;

    @GetMapping("/get-all-user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser=userServicesProvider.getService();
       return (allUser!=null && !allUser.isEmpty())
        ? new ResponseEntity<>(allUser,HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/create-admin")
    public ResponseEntity<User> createAdmin(@RequestBody User adminDetail){
       User saveAdmin= userServicesProvider.saveAdmin(adminDetail);
       return new ResponseEntity<>(saveAdmin,HttpStatus.CREATED);
    }

}
