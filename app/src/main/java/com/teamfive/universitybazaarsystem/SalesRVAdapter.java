package com.teamfive.universitybazaarsystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SalesRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<SalesItems> list = new ArrayList<>();

    public SalesRVAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<SalesItems> items) {
        list.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.salesrv, parent, false);
        return new SalesVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        SalesVH salesvh = (SalesVH) holder;
        SalesItems items = list.get(position);
        salesvh.txt_itemname.setText(new StringBuilder("Item Name: ").append(items.getItemName()));
        salesvh.txt_itemprice.setText(new StringBuilder("$").append(items.getItemPrice()));
        salesvh.txt_itemseller.setText(new StringBuilder("Item Seller: ").append(items.getItemSeller()));
        salesvh.addCart.setOnClickListener(v->{
            DAOCartList daocart = new DAOCartList();
            CartItems item = new CartItems(items.getItemName(),items.getItemPrice(),items.getItemSeller());
            daocart.add(item);
            DAOSalesItems salesdao = new DAOSalesItems();
            salesdao.remove(items.getKey()).addOnSuccessListener(suc->
            {
                Toast.makeText(context,"Item is removed",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(context,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });
    };

    public int getItemCount(){return list.size();}

}
