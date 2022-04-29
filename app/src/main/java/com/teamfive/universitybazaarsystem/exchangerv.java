package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class exchangerv extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<ExchangeItems> list = new ArrayList<>();

    public exchangerv(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<ExchangeItems> items) {
        list.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_exchangerv, parent, false);
        return new ExchangeVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ExchangeVH exchangeVH = (ExchangeVH) holder;
        ExchangeItems items = list.get(position);
        exchangeVH.txt_itemnameEX.setText(new StringBuilder("Item Name: ").append(items.getItemNameEX()));
        exchangeVH.txt_itemtrade.setText(new StringBuilder("Item: ").append(items.getItemtrade()));
        exchangeVH.txt_itemtrader.setText(new StringBuilder("Item Trader: ").append(items.getItemtrader()));
        exchangeVH.ExchangeItem.setOnClickListener(v->{
            DAOHistoryItems historydao = new DAOHistoryItems();
            HistoryItems item = new HistoryItems(items.getItemNameEX(),items.getItemtrade(),items.getItemtrader());
            historydao.add(item);
            DAOExchangeItems exchangedao = new DAOExchangeItems();
            exchangedao.remove(items.getKey()).addOnSuccessListener(suc->
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
