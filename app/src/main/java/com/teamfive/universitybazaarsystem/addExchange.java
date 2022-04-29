package com.teamfive.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class addExchange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exchange);
        final EditText ItemNameEX = findViewById(R.id.ItemNameEX);
        final EditText ItemExchange = findViewById(R.id.ItemEX);
        final EditText ItemTrader = findViewById(R.id.TraderName);
        Intent intent = new Intent(this, ExchangeActivity.class);
        Button ExchangeSubmititem = findViewById(R.id.ExchangeSubmitButton);
        DAOExchangeItems daoexchange = new DAOExchangeItems();

        ExchangeSubmititem.setOnClickListener(v ->
        {
            ExchangeItems item = new ExchangeItems(ItemNameEX.getText().toString(), ItemExchange.getText().toString(), ItemTrader.getText().toString());
            {
                daoexchange.add(item).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Item is submitted", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                });
            }

        });
    }
}