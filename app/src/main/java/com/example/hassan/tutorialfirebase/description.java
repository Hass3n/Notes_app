package com.example.hassan.tutorialfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class description extends AppCompatActivity {
   TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        txt=findViewById(R.id.txt3);
        String description=getIntent().getStringExtra("note");
        txt.setText(String.valueOf(description));
    }
}
