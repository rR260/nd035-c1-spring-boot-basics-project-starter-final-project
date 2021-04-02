package com.udacity.jwdnd.course1.cloudstorage.controllers;


import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NotesController {
    @Autowired
    NotesService notesService;

    @Autowired
    UserMapper userMapper;


    @PostMapping("/notes")
    public String postNotes(Authentication authentication , Model model,NotesForm notesForm) throws Exception {
        String username = authentication.getName();
        User user = userMapper.getUser(username);
        model.addAttribute("user",user);
        notesForm.setUserId(user.getUserId());
        System.out.println("USER ID: " + notesForm.getUserId());
        System.out.println("NOTE ID: " + notesForm.getNoteId());
        NotesForm notesForm1=new NotesForm(notesForm.getNoteId(),notesForm.getUserId(),notesForm.getNoteTitle(),notesForm.getNoteDescription());
        System.out.println("NOTE ID: " + notesForm1.getNoteId());
       if(notesForm1.getNoteId()==0){
           this.notesService.insert(notesForm1);
       }else{
           this.notesService.updateNotes(notesForm1.getNoteTitle(),notesForm1.getNoteDescription(), notesForm1.getNoteId());
       }
        System.out.println("NOTE ID: " + notesForm1.getNoteId());
        model.addAttribute("credentialForm",new CredentialForm());
        List<NotesForm> notes = notesService.getNotesWithUsername(user.getUserId());
        model.addAttribute("notes",notes);
    return "redirect:/home";
    }
@GetMapping("/delete-note/{noteId}")
    public  String deleteNotes(@PathVariable("noteId") int noteId){
if(noteId>0){
    notesService.deleteNotes(noteId);
}
return "redirect:/home";
}
}
