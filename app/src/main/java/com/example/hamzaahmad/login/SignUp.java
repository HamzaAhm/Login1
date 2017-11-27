package com.example.hamzaahmad.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText nameText, addressText, emailText, passwordText;
    Button login;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);

        nameText = (EditText) findViewById(R.id.name);
        addressText = (EditText) findViewById(R.id.address);
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);


        mAuth = FirebaseAuth.getInstance();


        databaseReference = FirebaseDatabase.getInstance().getReference();




        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String email = emailText.getText().toString();
        String pass = passwordText.getText().toString();

        if (view == login) {

            signUp(email, pass);



        }
        progressDialog.setMessage("Registering....");
        progressDialog.show();
    }

    public void userInfo() {
        String n = nameText.getText().toString();
        String a = addressText.getText().toString();

        UserInformation userr = new UserInformation();
        userr.setName(n);
        userr.setAddress(a);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child("users").child(user.getUid()).setValue(userr);

    }

    private void login(String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this, "Logged In", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, Wellcome.class);
                    startActivity(intent);
                    finish();


                }else{
                    String message = task.getException().getLocalizedMessage();
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void signUp(String email, String pass) {


        Task<AuthResult> task = mAuth.createUserWithEmailAndPassword(email, pass);

        task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {


                   userInfo();
                    String email = emailText.getText().toString();
                    String pass = passwordText.getText().toString();

                    login(email, pass);


                    Toast.makeText(SignUp.this, "Logged In", Toast.LENGTH_LONG).show();
                } else {
                    String message = task.getException().getLocalizedMessage();
                    Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
