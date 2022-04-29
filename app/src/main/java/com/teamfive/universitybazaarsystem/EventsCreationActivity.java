package com.teamfive.universitybazaarsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EventsCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_creation);
        EditText clubname = findViewById(R.id.clubnameinput);
        EditText eventname = findViewById(R.id.eventnameinput);
        EditText eventdate = findViewById(R.id.eventdateinput);
        EditText eventtime = findViewById(R.id.eventtimeinput);
        EditText eventcoordinator = findViewById(R.id.eventcoordinatorinput);
        EditText eventdescription = findViewById(R.id.eventdescriptioninput);
        EditText eventmembers = findViewById(R.id.eventmembersinput);
        Button B = findViewById(R.id.submiteventbutton);
        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> M = new HashMap<String, Object>();
                M.put("ClubName", clubname.getText().toString());
                M.put("EventName", eventname.getText().toString());
                M.put("EventDate", eventdate.getText().toString());
                M.put("EventTime", eventtime.getText().toString());
                M.put("EventCoordinator", eventcoordinator.getText().toString());
                M.put("EventDescription", eventdescription.getText().toString());
                M.put("EventMembers", eventmembers.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("Events").push().setValue(M);
            }
        });
    }
}