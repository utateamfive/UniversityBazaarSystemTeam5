package com.teamfive.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class addSales extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales);
        final EditText ItemName = findViewById(R.id.ItemName);
        final EditText ItemPrice = findViewById(R.id.ItemPrice);
        final EditText ItemSeller = findViewById(R.id.ItemSeller);
        Intent intent = new Intent(this, SalesActivity.class);
        Button SalesSubmitItem = findViewById(R.id.SalesSubmitButton);
        DAOSalesItems daosale = new DAOSalesItems();
        SalesSubmitItem.setOnClickListener(v ->
        {
            SalesItems item = new SalesItems(ItemName.getText().toString(), Integer.parseInt(ItemPrice.getText().toString()), ItemSeller.getText().toString());
            daosale.add(item).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "Item is submitted", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            });
        });

    }

}