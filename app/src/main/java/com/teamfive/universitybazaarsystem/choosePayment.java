package com.teamfive.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choosePayment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);
    }
    public void goCash(View view){
        Intent intent = new Intent (this, Cash.class);
        startActivity(intent);
    }
    public void goCard(View view){
        Intent intent = new Intent(this, CreditCard.class);
        startActivity(intent);
    }
    public void goPaypal(View view){
        Intent intent = new Intent(this, Paypal.class);
        startActivity(intent);
    }
}