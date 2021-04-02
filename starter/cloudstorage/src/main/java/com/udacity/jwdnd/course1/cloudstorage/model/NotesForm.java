package com.udacity.jwdnd.course1.cloudstorage.model;

public class NotesForm {
    private int userId;
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;

    public NotesForm( Integer noteId,int userId, String noteTitle, String noteDescription) {
        this.userId = userId;
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }
    public NotesForm(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
}
