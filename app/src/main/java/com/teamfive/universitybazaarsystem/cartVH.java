package com.teamfive.universitybazaarsystem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class cartVH extends RecyclerView.ViewHolder
{
    public TextView txt_itemnameCT, txt_itempriceCT, txt_itemsellerCT;
    public cartVH(@NonNull View itemView) {
        super(itemView);
        txt_itemnameCT = itemView.findViewById(R.id.txt_itemnameCT);
        txt_itempriceCT = itemView.findViewById(R.id.txt_itempriceCT);
        txt_itemsellerCT = itemView.findViewById(R.id.txt_itemsellerCT);
    }
}
