package com.G_Tech.day2LearningSpringBoot.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
@Document(collection = "users")
@Getter
@NoArgsConstructor
@Setter
public class User {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique =true)//so that every userName is Unique
    private String userName;
    @NonNull
    private String password;
    @DBRef//for Creating Reference of type journalEntries
    private List<JournalEntry> journalEntries=new ArrayList<>();

    private ArrayList<String> roles;

}
