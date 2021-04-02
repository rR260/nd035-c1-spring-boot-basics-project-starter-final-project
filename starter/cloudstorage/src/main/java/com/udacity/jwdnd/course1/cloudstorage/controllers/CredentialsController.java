package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CredentialsController {
    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping("/credentials")
    public String postCredential(Authentication authentication , Model model, CredentialForm credentialForm) throws Exception {
        String username = authentication.getName();
        User user = userMapper.getUser(username);
      //  model.addAttribute("user",user);
        credentialForm.setUserId(user.getUserId());
        String randomKey = RandomStringUtils.random(16, true, true);
        credentialForm.setKey(randomKey);
        credentialForm.setPassword(encryptionService.encryptValue(credentialForm.getPassword(),credentialForm.getKey()));
        CredentialForm credentialForm1=new CredentialForm(credentialForm.getCredentialId(),credentialForm.getUrl(),  credentialForm.getUserName(),credentialForm.getKey(),credentialForm.getPassword(),credentialForm.getUserId());
        if(credentialForm1.getCredentialId()==null){
            this.credentialService.insert(credentialForm1);
        }else{
            this.credentialService.update(credentialForm1);
        }
        model.addAttribute("credentialForm", new CredentialForm());
        List<CredentialForm> credentialForms = credentialService.getCredentialsByUserId(user.getUserId());
        model.addAttribute("credentials",credentialForms);
        return "redirect:/home";
    }
    @GetMapping("/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") String credentialId) {
        if (Integer.parseInt(credentialId) > 0) {
            credentialService.delete(Integer.parseInt(credentialId));
        }
        return "redirect:/home";
    }
}
