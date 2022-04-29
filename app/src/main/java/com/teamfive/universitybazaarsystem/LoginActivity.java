package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    /**
     * class that operates the login screen that handles user authentication,
     * going to register screen, or exit the app
     */

    //Called when the user taps the register button
    public void goToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    //Called when user successfully logs in
    public void goToHome(){
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView register;
        final Button exit;
        final Button login;
        final EditText editTextID;
        final EditText editTextPass;
        final FirebaseAuth uAuth;
        final FirebaseDatabase database;
        final DatabaseReference databaseReference;
        final ProgressBar progressBar;

        //database initializations
        uAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        editTextID = (EditText) findViewById(R.id.username);
        editTextPass = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //register button
        register = findViewById(R.id.registerButton);
        register.setEnabled(true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });

        //exit button
        exit = findViewById(R.id.exit);
        exit.setEnabled(true);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.this.finish();
                System.exit(0);
            }
        });

        //login button
        login = findViewById(R.id.login);
        login.setEnabled(true);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets credentials from textviews inputted by the user
                String ID = editTextID.getText().toString().trim();
                String password = editTextPass.getText().toString().trim();

                //checks if fields are empty
                if(ID.isEmpty()){
                    editTextID.setError("ID is required!");
                    editTextID.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    editTextPass.setError("Password is required!");
                    editTextPass.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //checks if inputted ID is an email or a user ID, both work as inputs
                if(Patterns.EMAIL_ADDRESS.matcher(ID).matches()){
                    uAuth.signInWithEmailAndPassword(ID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                goToHome();
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            }
                            else{
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginActivity.this, "Failed to login, Please check your credentials", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                else {
                    databaseReference.child("user_ids").child(ID).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot!=null){
                                String ID = snapshot.getValue(String.class);
                                uAuth.signInWithEmailAndPassword(ID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if(task.isSuccessful()){
                                            goToHome();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                        }
                                        else{
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Toast.makeText(LoginActivity.this, "Failed to login, Please check your credentials", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


    }
}