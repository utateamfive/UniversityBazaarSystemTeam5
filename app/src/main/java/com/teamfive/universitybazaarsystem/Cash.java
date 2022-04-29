package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cash extends AppCompatActivity {

    DAOCartList cartdao;
    DAOHistoryItems historydao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);
        cartdao = new DAOCartList();
    }
    public void returnSales(View view)
    {
        historydao = new DAOHistoryItems();
        DatabaseReference refrence = FirebaseDatabase.getInstance().getReference().child("CartItems");
        refrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<CartItems> items = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    CartItems item = data.getValue(CartItems.class);
                    item.setKey(data.getKey());
                    HistoryItems Hitems = new HistoryItems(item.getItemNameCT(),String.valueOf(item.getItemPriceCT()),item.getItemSellerCT());
                    historydao.add(Hitems);
                    cartdao.remove(item.getKey());
                }
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(this, SalesMain.class);
        startActivity(intent);
    }

}