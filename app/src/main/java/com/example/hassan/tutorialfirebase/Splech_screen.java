package com.example.hassan.tutorialfirebase;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splech_screen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splech_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splech_screen.this,signin.class));
                finish();
            }
        },3000);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {

        if(mAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(Splech_screen.this,signin.class));
        }
        super.onStart();
    }
}
