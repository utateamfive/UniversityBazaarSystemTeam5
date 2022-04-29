package com.teamfive.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class ClubsEventsHomepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_events_homepage);
    }

    public void goToClubsPage(View view){

        Intent I = new Intent(this, ClubsActivity.class);
        startActivity(I);
    }

    public void goToEventsPage(View view){

        Intent I = new Intent(this, EventsActivity.class);
        startActivity(I);
    }
}