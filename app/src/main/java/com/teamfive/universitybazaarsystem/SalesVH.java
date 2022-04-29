package com.teamfive.universitybazaarsystem;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

public class SalesVH extends RecyclerView.ViewHolder {
    public TextView txt_itemname, txt_itemprice, txt_itemseller;
    public Button addSale;
    public Button addCart;

    public SalesVH(@NonNull View itemView) {
        super(itemView);
        txt_itemname = itemView.findViewById(R.id.txt_itemname);
        txt_itemprice = itemView.findViewById(R.id.txt_itemprice);
        txt_itemseller = itemView.findViewById(R.id.txt_itemseller);
        addCart = itemView.findViewById(R.id.addCart);
    }
}
