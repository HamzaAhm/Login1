package com.example.hamzaahmad.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText emailView, passView;
    Button logBtn, signup;
    ProgressDialog progressDialog;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        emailView = (EditText)findViewById(R.id.email);
        passView = (EditText)findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();

        logBtn = (Button) findViewById(R.id.log);
        signup = (Button) findViewById(R.id.signUp);

        logBtn.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String e = emailView.getText().toString();
        String p = passView.getText().toString();

        if(view == logBtn){
            login(e,p);
        }
        else if(view == signup){
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            finish();
        }

        progressDialog.setMessage("wait while you logged in...");
        progressDialog.show();
    }

    private void login(String email, String pass) {

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Wellcome.class);
                    startActivity(intent);
                    finish();

                }else{
                    String message = task.getException().getLocalizedMessage();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
