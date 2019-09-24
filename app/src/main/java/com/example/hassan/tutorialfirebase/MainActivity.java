package com.example.hassan.tutorialfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = findViewById(R.id.ed1);
       /* ed2=findViewById(R.id.ed2);
        bt=findViewById(R.id.button1);*/
        // FirebaseDatabase database = FirebaseDatabase.getInstance();

        // final DatabaseReference myRef = database.getReference("name");

       /* bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ed1.getText().toString();
                String data=ed2.getText().toString();
                myRef.child("data").push().setValue(data);
                myRef.child("name").push().setValue(name);
            }
        });*/

        // read from firebase

       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // String value = dataSnapshot.getValue(String.class).toString();
                // to convert string using hash Map
             Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                ed1.setText(String.valueOf(map.values().toString()));
                Log.e("sucess", "Value is: " + map);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Log.e("error", "Failed to read value.", error.toException());
            }
        });




    }*/
    }
}
