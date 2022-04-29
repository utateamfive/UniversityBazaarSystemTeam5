package com.teamfive.universitybazaarsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TopicExchange extends AppCompatActivity implements OnItemClickListener{
    private RecyclerView topicrecycle;
    Topicadapter adapter;
    DatabaseReference topicdb;
    private Button create_button;
    private Button search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        topicdb = FirebaseDatabase.getInstance().getReference("Topic");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_exchange);
        create_button = findViewById(R.id.create_topic);
        topicrecycle = findViewById(R.id.listtopics);
        search_button = findViewById(R.id.search_button);
        topicrecycle.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Topic> options
                = new FirebaseRecyclerOptions.Builder<Topic>()
                .setQuery(topicdb, Topic.class)
                .build();
        adapter = new Topicadapter(options);
        topicrecycle.setAdapter(adapter);
        topicrecycle.setItemAnimator(null);
        adapter.setOnClickListener(this);
        create_button.setOnClickListener(v -> openCreate_Topic());
        search_button.setOnClickListener(v -> openSearch());
    }
//Add this function on the homepage for the search button and for items activity
    private void openSearch() {
        Intent search_intent = new Intent(this, search_activity.class);
        startActivity(search_intent);
    }


    private void openCreate_Topic() {
        Intent intent = new Intent(this, Create_Topic.class);
        startActivity(intent);
    }
    @Override protected void onStart()
    {
        super.onStart();
                adapter.startListening();
    }
    @Override protected void onStop()
    {
        super.onStop();
                adapter.stopListening();
    }
    @Override
    public void onClick(View view, int position) {
        Intent view_intent = new Intent(this, view_delete_topic.class);
        view_intent.putExtra("title", adapter.getItem(position).getTopicname());
        view_intent.putExtra("category",adapter.getItem(position).getTopiccategory());
        view_intent.putExtra("author", adapter.getItem(position).getTopicAuthor());
        view_intent.putExtra("bodytext", adapter.getItem(position).getBodytext());
        view_intent.putExtra("Key", adapter.getRef(position).getKey());
        startActivity(view_intent);

    }
}
