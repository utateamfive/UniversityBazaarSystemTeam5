package com.teamfive.universitybazaarsystem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExchangeVH extends RecyclerView.ViewHolder
{
    public TextView txt_itemnameEX, txt_itemtrade, txt_itemtrader;
    public Button ExchangeItem, ExchangeEdit;
    public ExchangeVH(@NonNull View itemView) {
        super(itemView);
        txt_itemnameEX = itemView.findViewById(R.id.txt_itemnameEX);
        txt_itemtrade = itemView.findViewById(R.id.txt_itemtrade);
        txt_itemtrader = itemView.findViewById(R.id.txt_itemtrader);
        ExchangeItem = itemView.findViewById(R.id.ExchangeItem);
    }
}
