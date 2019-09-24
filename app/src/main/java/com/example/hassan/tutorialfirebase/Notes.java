package com.example.hassan.tutorialfirebase;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class Notes extends AppCompatActivity  {

    FloatingActionButton floatingActionButton;
    DatabaseReference  myRef=null;



    ArrayList<Data>n;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    Noteadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView=findViewById(R.id.recycle);





        // write in firebase
        FirebaseDatabase database=FirebaseDatabase.getInstance();
       myRef=database.getReference("notes");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(Notes.this);
                LayoutInflater inflater=getLayoutInflater();
                View dialogview=inflater.inflate(R.layout.alart_dialog,null);
                builder.setCancelable(false);
                builder.setView(dialogview);
                final AlertDialog dialog=builder.create();
                dialog.show();
                Button add=dialogview.findViewById(R.id.button_add);
                final EditText ed1=dialogview.findViewById(R.id.edit1);
                final EditText ed2=dialogview.findViewById(R.id.edit2);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String title=ed1.getText().toString();
                        String note=ed2.getText().toString();

                        if(title.isEmpty() && note.isEmpty()) {
                           Toast.makeText(Notes.this,"empty",Toast.LENGTH_LONG).show();
                           ed1.setError("empty");
                           ed2.setError("empty");

                        }
                        else
                        {
                            String id = myRef.push().getKey();
                            Log.e("ID",id);
                            NoteData data = new NoteData();
                            data.setId(id);
                            data.setTitle(title);
                            data.setNote(note);
                            data.setDatatime(getcurrentData());

                            myRef.child(id).setValue(data);
                            dialog.dismiss();
                        }

                    }
                });




            }
        });

    }

    // read from firebase


    @Override
    protected void onStart() {

        n=new ArrayList<>();
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                       n.clear();
                    for (DataSnapshot dmo : dataSnapshot.getChildren()) {

                               NoteData nd = dmo.getValue(NoteData.class);
                               String n_Title = nd.getTitle();

                               String n_Note = nd.getNote();
                               String n_data=nd.getDatatime();
                               String n_id=nd.getId();


                               n.add(0,new Data(n_Title,n_Note,n_id,n_data));





                    }

                    linearLayoutManager=new LinearLayoutManager(Notes.this);
                    adapter=new Noteadapter(n);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    Onitemclick();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            super.onStart();

        }


        // get current data

      public  String getcurrentData()
      {
          Calendar calendar=Calendar.getInstance();
          SimpleDateFormat mdformat= new SimpleDateFormat("EEEE hh:mm a");
          String stdata=mdformat.format(calendar.getTime());
          return  stdata;


      }

     // Adapter item click

     public  void Onitemclick()
     {
        final EditText ed3,ed4;
         ed3=findViewById(R.id.edit3);
         ed4=findViewById(R.id.edit4);
         adapter.setOnItemClick(new Noteadapter.OnItemClick() {
             @Override
             public void onitemclick(final Data data, int postion) {
                 /*final Intent in=new Intent(Notes.this,description.class);
                 in.putExtra("note",data.getN_note());
                 startActivity(in);*/




                Log.e("title",data.N_Title);
                Log.e("note",data.N_note);
                Log.e("id",data.id);
                 AlertDialog.Builder builder=new AlertDialog.Builder(Notes.this);
                 LayoutInflater inflater=getLayoutInflater();
                 final View dialogview=inflater.inflate(R.layout.edit_alert,null);
                 builder.setCancelable(false);
                 builder.setView(dialogview);
                 final AlertDialog dialog=builder.create();
                 dialog.show();
                  EditText ed1=dialogview.findViewById(R.id.edit3);
                  EditText ed2=dialogview.findViewById(R.id.edit4);
                   String title=data.N_Title;
                   String note=data.N_note;
                   ed1.setText(String.valueOf(title));
                   ed2.setText(String.valueOf(note));
                   Button b_updata=dialogview.findViewById(R.id.updata);

                b_updata.setOnClickListener(new View.OnClickListener() {
                    EditText ed1=dialogview.findViewById(R.id.edit3);
                    EditText ed2=dialogview.findViewById(R.id.edit4);
                    @Override
                    public void onClick(View v) {

                        DatabaseReference updata_r=myRef.child(data.getId());
                        String title=ed1.getText().toString();
                        String note=ed2.getText().toString();

                        NoteData d=new NoteData(data.id,title,note,getcurrentData());
                       // updata_r.child(data.id).setValue(d);

                        updata_r.setValue(d);
                        dialog.dismiss();





                    }
                });
                 // delete item in firebase
                 Button delete=dialogview.findViewById(R.id.delete);
                 delete.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         myRef.child(data.id).removeValue();
                         dialog.dismiss();
                     }
                 });



             }
         });

     }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        if (item.getItemId() == R.id.item8) {
            startActivity(new Intent(Main2Activity.this, Login.class));
            return true;
        }*/
        if (item.getItemId() == R.id.item9) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Notes.this,signin.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }










}
