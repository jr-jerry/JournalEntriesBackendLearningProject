// package com.G_Tech.day2LearningSpringBoot.Controller;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.G_Tech.day2LearningSpringBoot.entity.JournalEntry;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PutMapping;

// @RestController
// @RequestMapping("/journal")
// public class JournalController {
//    Map<Long , JournalEntry> journalentries = new HashMap<>();
//    @GetMapping
//    public List<JournalEntry> getAll(){
//     return new ArrayList<>(journalentries.values());
//    }
//    @PostMapping
//    public boolean createJournalEntry(@RequestBody JournalEntry bodEntry) {
//        //TODO: process POST request
//        journalentries.put(bodEntry.getid(),bodEntry);
//        return true;
//    }
   
//     @GetMapping("id/{idValue}")
//     public JournalEntry getById(@PathVariable
//      Long idValue) {
//         return journalentries.get(idValue);
//     }
//     @DeleteMapping("id/{value}")
//     public JournalEntry deleteById(@PathVariable Long value){
//         return journalentries.remove(value);
//     }

//     @PutMapping("id/{id}")
//     public boolean updateEntry(@PathVariable Long id, @RequestBody JournalEntry bodyEntry) {
//         journalentries.put(id,bodyEntry);
//         return true;
//     }
    
    
// }
