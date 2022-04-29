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

public class Paypal extends AppCompatActivity {
    DAOCartList cartdao;
    DAOHistoryItems historydao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);
        final EditText paypalMail = findViewById(R.id.Paypal);
        final EditText paypalPass = findViewById(R.id.PaypalPass);
        cartdao = new DAOCartList();
        TextView fail = findViewById(R.id.failPay);
        Button submitCC = findViewById(R.id.SubmitPaymentPP);
        fail.setVisibility(View.INVISIBLE);
        submitCC.setOnClickListener(view ->
        {
            final int PayPal = paypalMail.getText().toString().length();
            final int PayPass = paypalPass.getText().toString().length();

            boolean pass = PayPal >= 1 && PayPass >= 1;
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
