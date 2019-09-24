package com.example.hassan.tutorialfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity {
    EditText ed1,ed2;
    Button btn,btn2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ed1=findViewById(R.id.emil_text);
        ed2=findViewById(R.id.password_text);
        btn=findViewById(R.id.sigin);
       btn2=findViewById(R.id.register2);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=ed1.getText().toString();
                String password=ed2.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                           verifyEmail();
                        }



                    }
                });
            }
        });



       btn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(signin.this,Auth.class));
           }
       });




    }


// verify email
    public void verifyEmail()
    {
        FirebaseUser user= mAuth.getCurrentUser();
        if(user.isEmailVerified())
        {
            startActivity(new Intent(signin.this,Notes.class));
        }
       else
        {

            Toast.makeText(signin.this,"please verfy email",Toast.LENGTH_LONG).show();
        }


    }




}
