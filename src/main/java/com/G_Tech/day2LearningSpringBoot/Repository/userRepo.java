package com.G_Tech.day2LearningSpringBoot.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.G_Tech.day2LearningSpringBoot.entity.User;


public interface userRepo extends MongoRepository<User,ObjectId>{
    Optional<User> findByUserName(String userName);

}
