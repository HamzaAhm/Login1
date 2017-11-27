package com.example.hamzaahmad.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Wellcome extends AppCompatActivity implements View.OnClickListener {

    Button logout;

    TextView wellText;

    Button find;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        logout = (Button)findViewById(R.id.logout);
        wellText = (TextView)findViewById(R.id.textView);
        find = (Button)findViewById(R.id.find_people);

         firebaseDatabase = FirebaseDatabase.getInstance();
         mDatabase = firebaseDatabase.getReference("users");

          mAuth = FirebaseAuth.getInstance();
          user = mAuth.getCurrentUser();


          mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.child(user.getUid()).child("name").getValue(String.class);
                    wellText.setText("Hello!  "+name);
              }

              @Override
              public void onCancelled(DatabaseError databaseError) {

              }
          });

          //wellText.setText("Wellcom .. "+user.getEmail());

        logout.setOnClickListener(this);
        find.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view == logout){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            if(view == find){
                Intent intent = new Intent(Wellcome.this, Recycle.class);
                startActivity(intent);
            }

        }

    }

    }


