package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {

    private NotesMapper notesMapper;
    public NotesService(NotesMapper notesMapper){
        this.notesMapper=notesMapper;
    }
    public List<NotesForm> getAllNotes() {
        return notesMapper.getAllNotes();
    }
    public  List<NotesForm>  getNotesWithUsername(Integer userId){
        return notesMapper.getNotesWithUsername(userId);
    }
    public  int insert(NotesForm notesForm){
        return notesMapper.insert(notesForm);
    }
    public int updateNotes(String noteTitle,String noteDescription,Integer noteId){
    return notesMapper.update(noteTitle,noteDescription,noteId);
    }
    public int deleteNotes(int noteId){
        return notesMapper.deleteNotes(noteId);
    }
}
