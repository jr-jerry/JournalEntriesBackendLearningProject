package com.G_Tech.day2LearningSpringBoot.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.G_Tech.day2LearningSpringBoot.Repository.journalRepository;
import com.G_Tech.day2LearningSpringBoot.Repository.userRepo;
import com.G_Tech.day2LearningSpringBoot.entity.JournalEntry;
import com.G_Tech.day2LearningSpringBoot.entity.User;
import com.G_Tech.day2LearningSpringBoot.services.JournalEntryServices;
import com.G_Tech.day2LearningSpringBoot.services.userServices;

import org.springframework.http.HttpStatus;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/Journal")
public class JournalControllerDB {


    @Autowired
    JournalEntryServices journalentryservices;

    @Autowired
    userRepo userRepoProvider;
    
    @Autowired
    userServices userServices;

    @Autowired
    journalRepository journalrepo;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<JournalEntry> postMethodName(@RequestBody JournalEntry entry) {
        Authentication authenticationProvider=SecurityContextHolder.getContext().getAuthentication();
        String userName=authenticationProvider.getName();
            try{
            JournalEntry createdEntry=journalentryservices.createentry(entry,userName);
            return new ResponseEntity<>(createdEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            throw new RuntimeException("Something wrong" ,e);
        }
      
    }


    @GetMapping
    public ResponseEntity<List<JournalEntry>>getMethodName() {
        Authentication authenticationProvider=SecurityContextHolder.getContext().getAuthentication();
        String userName=authenticationProvider.getName();
        User user=journalentryservices.getAllEntry(userName);
        
        if(user!=null){
            return new ResponseEntity<>(user.getJournalEntries(),HttpStatus.OK);
        }
       
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
    }
    @GetMapping("/{id}")
    public ResponseEntity <JournalEntry> getEntryByIdEndpoint(@PathVariable ObjectId id) {
        Authentication authenticationHolder=SecurityContextHolder.getContext().getAuthentication();
        String authenticateUserName=authenticationHolder.getName();

        User userInDb=userRepoProvider.findByUserName(authenticateUserName).orElse(null);
        
        if(userInDb==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean check=userInDb.getJournalEntries().stream().anyMatch(entry->entry.getid().equals(id));

       List<JournalEntry> foundEntryList=userInDb.getJournalEntries().stream().filter(entry->entry.getid().equals(id)).collect(Collectors.toList());//finding entry reference from User

       //finding entry from List of JournalEntry

       Optional<JournalEntry> journalFound=foundEntryList.stream().filter(entries->entries.getid().equals(id)).findFirst();


        if(!check){
            return new ResponseEntity<>(journalFound.get(),HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    


    @DeleteMapping("/{id}")
    public ResponseEntity<JournalEntry> delEntry(@PathVariable ObjectId id){
        Authentication authenticationProvider=SecurityContextHolder.getContext().getAuthentication();
        String userName=authenticationProvider.getName();

        User userInDb=userRepoProvider.findByUserName(userName).orElse(null);
        Boolean check=userInDb.getJournalEntries().stream().anyMatch(eachEntry->eachEntry.getid().equals(id));
        if(check){
            Optional<JournalEntry> entry=journalrepo.findById(id);
        if(entry.isPresent()){
                journalentryservices.delService(id,userName);
                return new ResponseEntity<>(entry.get(),HttpStatus.NO_CONTENT);

        }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    }
    @PutMapping("{id}")
    public ResponseEntity<JournalEntry> putMethodName(@PathVariable ObjectId id,@RequestBody JournalEntry entry) {

        Authentication authenticationProvider=SecurityContextHolder.getContext().getAuthentication();
        String userName=authenticationProvider.getName();

        User userInDb=userRepoProvider.findByUserName(userName).orElse(null);
        Boolean check=userInDb.getJournalEntries().stream().anyMatch(eachEntry->eachEntry.getid().equals(id));

        if(check){ 
            Optional<JournalEntry> checkEntry=journalrepo.findById(id);
                if(checkEntry.isPresent()){
                JournalEntry UpdateEntry= journalentryservices.putService(entry,id,userName); 
                    return new ResponseEntity<>(UpdateEntry,HttpStatus.CREATED);
                }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
        
    }
    
    
   

    
}
