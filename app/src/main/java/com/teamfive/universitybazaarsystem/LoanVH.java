package com.teamfive.universitybazaarsystem;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoanVH extends RecyclerView.ViewHolder
{
    public TextView txt_itemnameLN, txt_itemtime, txt_itemLoaner;
    public Button addLoan;
    public Button LoanItem;
    public LoanVH(@NonNull View itemView) {
        super(itemView);
        txt_itemnameLN = itemView.findViewById(R.id.txt_itemnameLN);
        txt_itemtime = itemView.findViewById(R.id.txt_itemtime);
        txt_itemLoaner = itemView.findViewById(R.id.txt_itemLoaner);
        LoanItem = itemView.findViewById(R.id.LoanItem);
    }
}
