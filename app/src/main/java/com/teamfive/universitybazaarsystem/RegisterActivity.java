package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    /**
     * class that handles user registration and stores user data to database
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText rName = (EditText) findViewById(R.id.nameBox);
        final EditText rEmail = (EditText) findViewById(R.id.emailBox);
        final EditText rPhone = (EditText) findViewById(R.id.phoneBox);
        final EditText rID = (EditText)  findViewById(R.id.IDBox);
        final EditText rPassword = (EditText)  findViewById(R.id.passwordBoxR);
        final FirebaseAuth uAuth;
        final FirebaseDatabase database;
        final DatabaseReference databaseReference;

        //database initializations
        uAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        DAOUser dao = new DAOUser();

        //reset button
        Button reset = findViewById(R.id.resetButton);
        reset.setEnabled(true);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rName.setText("");
                rEmail.setText("");
                rPhone.setText("");
                rID.setText("");
                rPassword.setText("");
            }
        });

        //submit button
        Button submit = findViewById(R.id.submitButton);
        submit.setEnabled(true);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence success = "User registered.";
                int duration = Toast.LENGTH_LONG;

                // getting user inputted data
                String name = rName.getText().toString();
                String email = rEmail.getText().toString();
                String phone = rPhone.getText().toString();
                String ID = rID.getText().toString();
                String pass = rPassword.getText().toString();

                // checks if fields are empty
                if(name.isEmpty()){
                    rName.setError("Full Name is Required");
                    return;
                }
                if (email.isEmpty()){
                    rEmail.setError("Email is Required");
                    return;
                }
                if (phone.isEmpty()){
                    rPhone.setError("Phone number is Required");
                    return;
                }
                if (ID.isEmpty()){
                    rID.setError("ID is Required");
                    return;
                }
                if (pass.isEmpty()){
                    rPassword.setError("Password is required");
                    return;
                }

                // creating a new user
                uAuth.createUserWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //initializing user class
                        User user = new User();
                        user.setUserName(name);
                        user.setUserEmail(email);
                        user.setUserPhone(phone);
                        user.setUserID(ID);
                        user.setUserPass(pass);

                        //used to support authentication with an ID
                        databaseReference.child("user_ids").child(ID).setValue(email);

                        // adds user data to database
                        dao.add(user);
                        Toast toast = Toast.makeText(context, success, duration);
                        toast.show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(context, ""+e.getMessage(), duration);
                        toast.show();
                    }
                });

                finish();
            }
        });
    }
}