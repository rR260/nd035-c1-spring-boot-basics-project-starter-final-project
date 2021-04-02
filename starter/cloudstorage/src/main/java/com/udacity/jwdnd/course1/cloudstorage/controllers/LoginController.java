package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
private UserService userService;
public  LoginController(UserService userService){
    this.userService=userService;
}
    @GetMapping()
    public String loginView() {
        return "login";
    }
//    @PostMapping()
//    public String postLogin(@ModelAttribute User user1, Model model){
//    boolean login=false;
//       User user= userService.getUser(user1.getUsername());
//       if(user!=null){
//           login=true;
//           model.addAttribute("loginError",false);
//           System.out.println(user1.getUsername());
//           System.out.println(login);
//       }else{
//           System.out.println("true");
//           model.addAttribute("loginError",true);
//       }
//        return "login";
//    }
}
