package com.example.hassan.tutorialfirebase;

import java.io.Serializable;
import java.util.Map;

public class  NoteData implements Serializable {
     public String id;
    public   String title;
    public String note;

    public String datatime;


    public NoteData()

   {

   }

    public NoteData(String id, String title, String note,String datatime ) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.datatime = datatime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }
}
