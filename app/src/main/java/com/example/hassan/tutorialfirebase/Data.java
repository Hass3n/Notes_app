package com.example.hassan.tutorialfirebase;

import java.util.Map;

public class Data {

   String N_Title;
   String N_note;
   String id;
    public  String N_data;

    public Data()
    {


    }
    public Data(String n_Title, String n_note, String id, String n_data) {

        N_Title = n_Title;
        N_note = n_note;
        this.id = id;
        N_data = n_data;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getN_Title() {
        return N_Title;
    }

    public void setN_Title(String n_Title) {
        N_Title = n_Title;
    }

    public String getN_note() {
        return N_note;
    }

    public void setN_note(String n_note) {
        N_note = n_note;
    }


    public String getN_data() {
        return N_data;
    }

    public void setN_data(String n_data) {
        N_data = n_data;
    }
}
