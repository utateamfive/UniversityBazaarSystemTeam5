package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreditCard extends AppCompatActivity {
    DAOCartList cartdao;
    DAOHistoryItems historydao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        final EditText cardnumber = findViewById(R.id.CCnumber);
        final EditText cardccv = findViewById(R.id.CCccv);
        final EditText carddate = findViewById(R.id.CCdate);
        TextView fail = findViewById(R.id.payFail);
        Button submitCC = findViewById(R.id.SubmitPaymentCC);
        fail.setVisibility(View.INVISIBLE);
        cartdao = new DAOCartList();
        submitCC.setOnClickListener(view ->
        {
            final int CardNumber = cardnumber.getText().toString().length();
            final int CardCCV = cardccv.getText().toString().length();
            final int CardDate = carddate.getText().toString().length();
            boolean pass = CardNumber == 16 && CardCCV == 3 && CardDate == 4;
            if(pass)
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
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Intent intent = new Intent(this, SalesMain.class);
                startActivity(intent);
            }
            else if (!pass)
            {
                fail.setVisibility(View.VISIBLE);
            }
        });
    }
}
