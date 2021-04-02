package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.controllers.CredentialsController;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<CredentialForm> getCredentialsWithUsername(int userId);

    @Select("Select * from CREDENTIALS")
    List<CredentialForm> getAllCredentials();

    @Select("Select * from NOTES where noteId=#{noteId}")
    List<NotesForm> getAllNotesWithNoteId(int noteId);

    @Delete("Delete from CREDENTIALS where credentialId=#{credentialId}")
    int deleteCredential(int credentialId);

    @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUES(#{url},#{userName},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(CredentialForm credentialForm);

    @Update("UPDATE CREDENTIALS " +
            "SET url = #{url}, username = #{username} ,key = #{key} ,password = #{password}" +
            "WHERE credentialId = #{credentialId}")
    int update(String url, String username, String key, String password,int credentialId);
}
