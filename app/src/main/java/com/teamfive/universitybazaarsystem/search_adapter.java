package com.teamfive.universitybazaarsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class search_adapter extends RecyclerView.Adapter<search_adapter.searchviewholder> implements Filterable{

    private ArrayList<Topic> topicList;
    private ArrayList<Topic> topicListfull;
    Context context;
    public search_adapter(Context context, ArrayList<Topic> list) {
        this.context = context;
        this.topicListfull = list;
        this.topicList = new ArrayList<>(topicListfull);

    }

    @NonNull
    @Override
    public searchviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchcard,parent, false);
        return new search_adapter.searchviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchviewholder holder, int position) {
        Topic model = topicList.get(position);
        holder.title.setText(model.getTopicname());
        holder.author.setText(model.getTopicAuthor());
        holder.category.setText(model.getTopiccategory());
        holder.body.setText(model.getBodytext());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    @Override
    public Filter getFilter() {
        return searchfilter;
    }

    public class searchviewholder extends RecyclerView.ViewHolder{
        TextView title, author, category, body;
        public searchviewholder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.card_title);
            author = itemView.findViewById(R.id.card_authorname);
            category= itemView.findViewById(R.id.card_category);
            body = itemView.findViewById(R.id.card_description);
        }
    }
    private Filter searchfilter = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence constraint){
            List<Topic> filteredlist = new ArrayList<>();
            if ((constraint == null) || constraint.length() == 0){
            filteredlist.addAll(topicListfull);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim();
                for (Topic item : topicListfull){
                    if (item.getTopicname().toLowerCase().contains(filterPattern)){
                        filteredlist.add(item);
                    }
                    else if (item.getTopiccategory().toLowerCase().contains(filterPattern)) {
                        filteredlist.add(item);
                    }
                    else if (item.getTopicAuthor().toLowerCase().contains(filterPattern)) {
                        filteredlist.add(item);
                    }
                       else if (item.getBodytext().toLowerCase().contains(filterPattern)) {
                           filteredlist.add(item); }
                }
            }FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }
        protected void publishResults(CharSequence constraint, FilterResults results){
            topicList.clear();
            topicList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
