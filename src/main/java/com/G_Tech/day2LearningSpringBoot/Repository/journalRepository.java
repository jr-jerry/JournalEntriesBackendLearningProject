package com.G_Tech.day2LearningSpringBoot.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.G_Tech.day2LearningSpringBoot.entity.JournalEntry;

public interface journalRepository extends MongoRepository<JournalEntry,ObjectId> {

}
