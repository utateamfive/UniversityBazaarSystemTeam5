package com.teamfive.universitybazaarsystem;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Topicadapter extends FirebaseRecyclerAdapter<Topic, Topicadapter.topicViewHolder>{

    private OnItemClickListener clickListener;
    public Topicadapter(@NonNull FirebaseRecyclerOptions<Topic> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull topicViewHolder holder, int position, @NonNull Topic instance)
    {
        holder.title.setText(instance.getTopicname());
        holder.author.setText(instance.getTopicAuthor());
        holder.category.setText(instance.getTopiccategory());
        holder.body.setText(instance.getBodytext());
    }
    @NonNull
    @Override
    public topicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topicinfo,parent, false);
        return new Topicadapter.topicViewHolder(view);

    }

    public void setOnClickListener(OnItemClickListener clickListener)
    {
        this.clickListener = clickListener;
    }


    class topicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, author, category, body;
        public topicViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title_view);
            author = itemView.findViewById(R.id.author_view);
            category= itemView.findViewById(R.id.category_view);
            body = itemView.findViewById(R.id.body_view);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);

        }

       @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getBindingAdapterPosition());
        }
    }
}
