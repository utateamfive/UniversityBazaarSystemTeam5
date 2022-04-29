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

public class ClubsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
    }

    public void goToClubsCreationPage(View view) {
        Intent I = new Intent(this, ClubsCreationActivity.class);
        startActivity(I);
    }

    public void JoinClub(View view) {
        EditText Club = findViewById(R.id.joinclubinput);
        String ClubtoJoin = Club.getText().toString();
        EditText Name = findViewById(R.id.joinnameinput);
        String NewName = Name.getText().toString();
        Map<String, Object> childUpdates = new HashMap<String, Object>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Clubs");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    String MemberString = snapshot.child("ClubMembers").getValue().toString();
                    String ClubString = snapshot.child("ClubName").getValue().toString();
                    if(ClubtoJoin.equals(ClubString)) {
                        childUpdates.put(key + "/ClubMembers/", MemberString + ", " + NewName);
                        databaseReference.updateChildren(childUpdates);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void PopulateActiveClubs(final View view) {
        RecyclerView recyclerview;
        DatabaseReference databaseReference;
        myadapter adapter;
        ArrayList<Club> list;

        recyclerview = findViewById(R.id.clubsview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Clubs");
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new myadapter(this, list);
        recyclerview.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Club C = dataSnapshot.getValue(Club.class);
                    list.add(C);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}