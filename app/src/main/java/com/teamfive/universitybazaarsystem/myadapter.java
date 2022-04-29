package com.teamfive.universitybazaarsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    Context context;
    ArrayList<Club> list;

    public myadapter(Context context, ArrayList<Club> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Club clubs = list.get(position);
        holder.name.setText(clubs.getClubName());
        holder.type.setText(clubs.getClubType());
        holder.description.setText(clubs.getClubDescription());
        holder.members.setText(clubs.getClubMembers());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name, type, description, members;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvClubName);
            type = itemView.findViewById(R.id.tvClubType);
            description = itemView.findViewById(R.id.tvClubDescription);
            members = itemView.findViewById(R.id.tvClubMembers);
        }
    }
}
