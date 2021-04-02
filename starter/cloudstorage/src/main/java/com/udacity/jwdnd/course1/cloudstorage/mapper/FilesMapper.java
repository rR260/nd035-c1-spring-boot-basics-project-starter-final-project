package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface FilesMapper {

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata) VALUES (#{fileName},#{contentType},#{fileSize},#{userId},#{fileData})" )
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(FileForm fileForm);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<FileForm> getFilesWithUsername(int userId);
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    FileForm getFilesWithFileName(String fileName);

    @Select("Select * from FILES")
    List<FileForm> getAllFiles();

    @Select("Select * from FILES where fileId=#{fileId}")
    List<FileForm> getAllFilesWithFileId(int fileId);

    @Delete("Delete from FILES where fileId=#{fileId}")
    int deleteFiles(int fileId);


}
