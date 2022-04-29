package com.teamfive.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ImageView commButton = (ImageView) findViewById(R.id.commView);
        final ImageView searchButton = (ImageView) findViewById(R.id.searchView);
        final ImageView clubsButton = (ImageView) findViewById(R.id.clubsView);
        final ImageView exchangeButton = (ImageView) findViewById(R.id.exchangeView);
        final ImageView salesButton = (ImageView) findViewById(R.id.salesView);
        final ImageView paymentButton = (ImageView) findViewById(R.id.payView);

        commButton.setEnabled(true);
        commButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), Communications.class);
                startActivity(I);
            }
        });

        searchButton.setEnabled(true);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), search_activity.class);
                startActivity(I);
            }
        });

        clubsButton.setEnabled(true);
        clubsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), ClubsEventsHomepage.class);
                startActivity(I);
            }
        });

        exchangeButton.setEnabled(true);
        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), TopicExchange.class);
                startActivity(I);
            }
        });

        salesButton.setEnabled(true);
        salesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), SalesMain.class);
                startActivity(I);
            }
        });

        paymentButton.setEnabled(true);
        paymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(view.getContext(), choosePayment.class);
                startActivity(I);
            }
        });
    }
}