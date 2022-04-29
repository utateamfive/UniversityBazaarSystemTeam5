package com.teamfive.universitybazaarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addLoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_loan);
        final EditText ItemNameLN = findViewById(R.id.ItemNameLN);
        final EditText Itemtime = findViewById(R.id.Itemtime);
        final EditText ItemLoaner = findViewById(R.id.ItemLoaner);
        Intent intent = new Intent(this, LoanActivity.class);
        Button LoanSubmititem = findViewById(R.id.LoanSubmitButton);
        DAOLoanItems daoloan = new DAOLoanItems();
        LoanSubmititem.setOnClickListener(v ->
        {
            LoanItems item = new LoanItems(ItemNameLN.getText().toString(), Integer.parseInt(Itemtime.getText().toString()), ItemLoaner.getText().toString());
            daoloan.add(item).addOnSuccessListener(suc ->
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