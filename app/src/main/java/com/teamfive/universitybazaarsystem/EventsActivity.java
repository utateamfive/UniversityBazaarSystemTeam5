package com.teamfive.universitybazaarsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    public void goToEventsCreationPage(View view) {

        Intent I = new Intent(this, EventsCreationActivity.class);
        startActivity(I);
    }

    public void JoinEvent(View view) {
        EditText Event = findViewById(R.id.joineventinput);
        String EventToJoin = Event.getText().toString();
        EditText Name = findViewById(R.id.joineventnameinput);
        String NewName = Name.getText().toString();
        Map<String, Object> childUpdates = new HashMap<String, Object>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Events");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    String MemberString = snapshot.child("EventMembers").getValue().toString();
                    String EventString = snapshot.child("EventName").getValue().toString();
                    if(EventToJoin.equals(EventString)) {
                        childUpdates.put(key + "/EventMembers/", MemberString + ", " + NewName);
                        databaseReference.updateChildren(childUpdates);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void PopulateActiveEvents(final View view) {
        RecyclerView recyclerview;
        DatabaseReference databaseReference;
        myadapter2 adapter;
        ArrayList<Event> list;

        recyclerview = findViewById(R.id.eventsview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Events");
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new myadapter2(this, list);
        recyclerview.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Event E = dataSnapshot.getValue(Event.class);
                    list.add(E);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}