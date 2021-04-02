package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class FilesController {
    @Autowired
    FileService fileService;
    @Autowired
    UserMapper userMapper;

    @PostMapping("/files")
    public String getFiles(Authentication authentication , @ModelAttribute("newFile") FileForm newFile, MultipartFile fileUpload) throws Exception{
        String username=authentication.getName();
        User user = userMapper.getUser(username);
        if(fileUpload.isEmpty()) {
            return "redirect:/home/result?isSuccess=" + false + "&errorType=" + 1;
        }
        List<FileForm> fileFormList=fileService.getAllFiles(user.getUserId());
        String fileName = fileUpload.getOriginalFilename();
        boolean fileIsDuplicate = false;
        for (FileForm file: fileFormList) {
            if (file.getFileName().equals(fileName)) {
                fileIsDuplicate = true;
                return "redirect:/home/result?isSuccess=" + false + "&errorType=" + 2;
//                break;
            }
        }
        fileService.addFile(fileUpload, username);
        Boolean isSuccess = true;

        // make sure to use redirect:/, as with just "/home" the Controller wouldn't be invoked at the backend
        return "redirect:/home";

    }
    @GetMapping("/delete-file/{fileId}")
    public String deleteFile(@PathVariable("fileId") int fileId) {
        if (fileId > 0) {
            fileService.deleteFile(fileId);
            Boolean isSuccess = true;
            return "redirect:/home";
        }
        Boolean isSuccess = false;
        return "redirect:/home/result?isSuccess=" + false + "&errorType=" + 1;
    }
     @GetMapping("home/get-file/{fileName}")
     @ResponseBody
     public byte[] getFile(@PathVariable("fileName") String fileName) {
        return fileService.getFiles(fileName).getFileData();
    }
}
