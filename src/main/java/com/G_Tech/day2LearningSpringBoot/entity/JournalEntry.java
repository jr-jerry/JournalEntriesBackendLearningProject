package com.G_Tech.day2LearningSpringBoot.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="JournalEntries")
@Data
public class JournalEntry {
    @Id
    private ObjectId id;
    private String name;
    private LocalDateTime time;
    public void setTime(LocalDateTime time){
        this.time=time;
    }
    public LocalDateTime getTime(){
        return time;
    }
    public void setid(ObjectId idValue){
        this.id=idValue;
    }
    public ObjectId getid(){
        return id;
    }
    public void setname(String nameValue){
        this.name=nameValue;
    }
    public String getname(){
        return name;
    }

    
}
