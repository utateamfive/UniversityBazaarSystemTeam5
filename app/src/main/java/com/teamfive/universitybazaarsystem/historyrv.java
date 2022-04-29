package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class historyrv extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context context;
    ArrayList<HistoryItems> list = new ArrayList<>();

    public historyrv(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<HistoryItems> items) {
        list.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_historyrv, parent, false);
        return new historyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        historyVH historyvh = (historyVH) holder;
        HistoryItems items = list.get(position);
        historyvh.txt_itemnameH.setText(new StringBuilder("Item Name: ").append(items.getItemNameH()));
        historyvh.txt_itemvalueH.setText(new StringBuilder("$/Days/Item: ").append(items.getItemValue()));
        historyvh.txt_itemsellerH.setText(new StringBuilder("Item Seller:").append(items.getItemSellerH()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}