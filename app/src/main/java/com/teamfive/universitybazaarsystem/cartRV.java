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

public class cartRV extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    ArrayList<CartItems> list = new ArrayList<>();

    public cartRV(Context ctx) {
        this.context = ctx;
    }

    public void setItems(ArrayList<CartItems> items) {
        list.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_cart_rv, parent, false);
        return new cartVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        cartVH cartvh = (cartVH) holder;
        CartItems items = list.get(position);
        cartvh.txt_itemnameCT.setText(new StringBuilder("Item Name: ").append(items.getItemNameCT()));
        cartvh.txt_itempriceCT.setText(new StringBuilder("$").append(items.getItemPriceCT()));
        cartvh.txt_itemsellerCT.setText(new StringBuilder("Item Seller: ").append(items.getItemSellerCT()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}