package com.G_Tech.day2LearningSpringBoot.services;

import java.util.Arrays;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.G_Tech.day2LearningSpringBoot.Repository.userRepo;
import com.G_Tech.day2LearningSpringBoot.entity.User;

@Component
public class userServices {
    public static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    userRepo userRepo;
    @Autowired
    userServices userServicesProvider;

    public List<User> getService(){
        return userRepo.findAll(); 
    }

    public User saveNewUser(User userBody){
        
        userBody.setPassword(passwordEncoder.encode(userBody.getPassword()));
        userBody.setRoles(Arrays.asList("User"));
        return userRepo.save(userBody);
    }
    public User saveUser(User userBody){
        return userRepo.save(userBody);
    }
    
    public User saveAdmin(User adminDetial){
        adminDetial.setPassword(passwordEncoder.encode(adminDetial.getPassword()));
        List<String> userRole=new ArrayList<>();
        userRole.add("user");
        userRole.add("admin");
        adminDetial.setRoles(userRole);
       return userRepo.save(adminDetial);
    }

    public User delService(String userName){
        Optional<User> delUser=userRepo.findById(new ObjectId(userName));
        if(userRepo.findById(new ObjectId(userName)).isPresent()){
        userRepo.deleteById(new ObjectId(userName));
            return delUser.orElse(null);
        }
        return null;
    
    }
    public User putService(User userbody, String userName){
        Optional<User> oldUser=userRepo.findByUserName(userName);
        if(oldUser.isPresent())
        {
            if(userbody.getUserName()!=null && !userbody.getPassword().equals("")){
                oldUser.get().setUserName(userbody.getUserName());
                oldUser.get().setPassword(userbody.getPassword());
                return userServicesProvider.saveNewUser(oldUser.get());
                
            }

        }
        return null;
    }
    
    public Optional<User> getUserByUsername(String username){
        return userRepo.findByUserName(username).isPresent()?userRepo.findByUserName(username):null;
    }
}
