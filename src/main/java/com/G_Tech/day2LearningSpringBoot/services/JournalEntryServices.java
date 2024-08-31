package com.G_Tech.day2LearningSpringBoot.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.G_Tech.day2LearningSpringBoot.entity.JournalEntry;
import com.G_Tech.day2LearningSpringBoot.entity.User;
import com.G_Tech.day2LearningSpringBoot.Repository.journalRepository;
import com.G_Tech.day2LearningSpringBoot.Repository.userRepo;

import java.time.LocalDateTime;
@Component
public class JournalEntryServices {
    @Autowired userRepo userRepo;
    @Autowired journalRepository journalrepo;


    public JournalEntry createentry( JournalEntry entry,String username){

        User foundUser=userRepo.findByUserName(username).orElse(null);
        //this entry is saved in JournalEntry collection
        entry.setTime(LocalDateTime.now());

        JournalEntry journalEntrySaved=journalrepo.save(entry);
        //this reference of savedJournalEntry saved in User collection

        foundUser.getJournalEntries().add(journalEntrySaved);
        // foundUser.setUserName(null);//jaan much k kr rha hu ttaki transection sikh saku 
        userRepo.save(foundUser);
        return journalEntrySaved;

    }
    
    public User getAllEntry(String username){
        User user=userRepo.findByUserName(username).orElse(null);
        return user;
    }

    public JournalEntry delService(ObjectId id,String username){

       JournalEntry DeletedEntry= journalrepo.findById(id).orElse(null);
       User deletedEntryUser=userRepo.findByUserName(username).orElse(null);

       deletedEntryUser.getJournalEntries().removeIf(x->x.getid().equals(id));
       
       journalrepo.deleteById(id);
       userRepo.save(deletedEntryUser);
        return DeletedEntry;
    }
    public JournalEntry putService(JournalEntry entry ,ObjectId value,String username){
        JournalEntry oldEntry=journalrepo.findById(value).orElse(null);
        User foundUser=userRepo.findByUserName(username).orElse(null);
        oldEntry.setname((entry.getname() != null && !entry.getname().equals(""))? entry.getname():oldEntry.getname());

        journalrepo.save(oldEntry);
        userRepo.save(foundUser);
        return journalrepo.save(oldEntry);
        
    }

}
