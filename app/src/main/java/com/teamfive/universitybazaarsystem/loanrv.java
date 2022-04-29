package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class loanrv extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private Context context;
    ArrayList<LoanItems> list = new ArrayList<>();

    public loanrv(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<LoanItems> items) {
        list.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_loanrv, parent, false);
        return new LoanVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LoanVH loanVH = (LoanVH) holder;
        LoanItems items = list.get(position);
        loanVH.txt_itemnameLN.setText(new StringBuilder("Item Name: ").append(items.getItemNameLN()));
        loanVH.txt_itemtime.setText(new StringBuilder("Days: ").append(items.getItemtime()));
        loanVH.txt_itemLoaner.setText(new StringBuilder("Item Loaner: ").append(items.getItemLoaner()));
        loanVH.LoanItem.setOnClickListener(v->{
            DAOHistoryItems historydao = new DAOHistoryItems();
            HistoryItems item = new HistoryItems(items.getItemNameLN(),String.valueOf(items.getItemtime()),items.getItemLoaner());
            historydao.add(item);
            DAOLoanItems loandao = new DAOLoanItems();
            loandao.remove(items.getKey()).addOnSuccessListener(suc->
            {
                Toast.makeText(context,"Item is removed",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(context,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}