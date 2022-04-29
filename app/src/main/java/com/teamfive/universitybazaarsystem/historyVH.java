package com.teamfive.universitybazaarsystem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class historyVH extends RecyclerView.ViewHolder
{
    public TextView txt_itemnameH, txt_itemvalueH,txt_itemsellerH;
    public historyVH(@NonNull View itemView) {
        super(itemView);
        txt_itemnameH = itemView.findViewById(R.id.txt_itemnameH);
        txt_itemvalueH = itemView.findViewById(R.id.txt_itemvalueH);
        txt_itemsellerH = itemView.findViewById(R.id.txt_itemsellerH);
    }
}
