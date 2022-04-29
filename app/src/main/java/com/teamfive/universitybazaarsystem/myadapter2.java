package com.teamfive.universitybazaarsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter2 extends RecyclerView.Adapter<myadapter2.myviewholder>
{
    Context context;
    ArrayList<Event> list;

    public myadapter2(Context context, ArrayList<Event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlerow2, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Event events = list.get(position);
        holder.name.setText(events.getClubName());
        holder.name2.setText(events.getEventName());
        holder.description.setText(events.getEventDescription());
        holder.date.setText(events.getEventDate());
        holder.time.setText(events.getEventTime());
        holder.coordinator.setText(events.getEventCoordinator());
        holder.members.setText(events.getEventMembers());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name, name2, description, date, time, coordinator, members;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvClubName);
            name2 = itemView.findViewById(R.id.tvEventName);
            description = itemView.findViewById(R.id.tvEventDescription);
            date = itemView.findViewById(R.id.tvEventDate);
            time = itemView.findViewById(R.id.tvEventTime);
            coordinator = itemView.findViewById(R.id.tvEventCoordinator);
            members = itemView.findViewById(R.id.tvEventMembers);
        }
    }
}
