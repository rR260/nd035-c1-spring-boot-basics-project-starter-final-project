package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Service
public class FileService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FilesMapper filesMapper;

    public List<FileForm> getAllFiles(Integer userId) {
        List<FileForm> file = filesMapper.getFilesWithUsername(userId);
        return file;
    }

    public void addFile(MultipartFile multipartFile, String userName) throws Exception {
        InputStream fis = multipartFile.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = fis.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        byte[] fileData = buffer.toByteArray();

        String fileName = multipartFile.getOriginalFilename();
        String contentType = multipartFile.getContentType();
        String fileSize = String.valueOf(multipartFile.getSize());
        Integer userId = userMapper.getUser(userName).getUserId();
        FileForm file = new FileForm(fileName, contentType, fileSize, userId, fileData);
        filesMapper.insert(file);

    }

    public int deleteFile(int fileId) {
        return filesMapper.deleteFiles(fileId);
    }

    public FileForm getFiles(String fileName) {
        return filesMapper.getFilesWithFileName(fileName);
    }
}
