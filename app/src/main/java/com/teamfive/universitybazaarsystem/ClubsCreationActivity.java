package com.teamfive.universitybazaarsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class ClubsCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_creation);
        EditText clubname = findViewById(R.id.clubnameinput);
        EditText clubtype = findViewById(R.id.clubtypeinput);
        EditText clubfounder = findViewById(R.id.clubfounderinput);
        EditText clubdescription = findViewById(R.id.clubdescriptioninput);
        EditText clublocation = findViewById(R.id.clublocationinput);
        EditText clubmembers = findViewById(R.id.clubmembersinput);
        Button B = findViewById(R.id.submitclubbutton);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View view) {
                HashMap<String, Object> M = new HashMap<String, Object>();
                M.put("ClubName", clubname.getText().toString());
                M.put("ClubType", clubtype.getText().toString());
                M.put("ClubFounder", clubfounder.getText().toString());
                M.put("ClubDescription", clubdescription.getText().toString());
                M.put("ClubLocation", clublocation.getText().toString());
                M.put("ClubMembers", clubmembers.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Clubs").push().setValue(M);
            }
        });
    }
}