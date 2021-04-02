package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final NotesService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, NotesService noteService, FileService fileService, CredentialService credentialService, UserMapper userMapper, AuthenticationService authenticationService, EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String getHomePage(Authentication authentication, Model model) throws Exception {
        String username = authentication.getName();
        User user = userMapper.getUser(username);
    List<FileForm> files = fileService.getAllFiles(user.getUserId());
    List<NotesForm> notes = noteService.getNotesWithUsername(user.getUserId());
    List<CredentialForm> credentials = credentialService.getCredentialsByUserId(user.getUserId());
        // as we will be using the contents on the home html page we need to use the addAttribute method:
        model.addAttribute("fileForm", new FileForm());
        model.addAttribute("files", files);
        model.addAttribute("noteForm", new NotesForm());
        model.addAttribute("notes", notes);
        model.addAttribute("credentialForm", new CredentialForm());
        model.addAttribute("credentials", credentials);
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }
}
