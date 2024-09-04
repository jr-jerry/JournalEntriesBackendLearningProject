package com.G_Tech.day2LearningSpringBoot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.G_Tech.day2LearningSpringBoot.Repository.userRepo;
import com.G_Tech.day2LearningSpringBoot.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    userRepo userrepo;
    @Override
    
    public 	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        User user =userrepo.findByUserName(username).orElse(null);
        if(user !=null ){
            UserDetails userDetails =org.springframework.security.core.userdetails.User.builder()
            .username(user.getUserName())
            .password(user.getPassword())
            .roles(user.getRoles().toArray(new String[0]))
            .build();
            return userDetails;

            
        }
         throw new UsernameNotFoundException("user not found");
    }

}
