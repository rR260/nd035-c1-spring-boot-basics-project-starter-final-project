package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface NotesMapper {
    @Select("SELECT * FROM NOTES WHERE userid = #{userId}")
    List<NotesForm> getNotesWithUsername(int userId);

    @Select("Select * from NOTES")
    List<NotesForm> getAllNotes();

    @Select("Select * from NOTES where noteId=#{noteId}")
    List<NotesForm> getAllNotesWithNoteId(int noteId);

    @Delete("Delete from NOTES where noteId=#{noteId}")
    int deleteNotes(int noteId);

    @Insert("INSERT INTO NOTES (notetitle,notedescription,userid) VALUES(#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(NotesForm notesForm);

    @Update("UPDATE NOTES " +
            "SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} " +
            "WHERE noteId = #{noteId}")
    int update(String noteTitle, String noteDescription, Integer noteId);
}
