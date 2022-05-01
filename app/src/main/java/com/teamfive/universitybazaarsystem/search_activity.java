package com.teamfive.universitybazaarsystem;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class search_activity extends AppCompatActivity{
    DatabaseReference topicreference;
    private search_adapter adapter;
    RecyclerView searchrecyclerview;
    ArrayList<Topic> List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List = new ArrayList<>();
        setContentView(R.layout.activity_search);
        topicreference = FirebaseDatabase.getInstance().getReference("Topic");
        searchrecyclerview = findViewById(R.id.search_list);
        searchrecyclerview.setLayoutManager(
                new LinearLayoutManager(this));
        super.onCreate(savedInstanceState);
        adapter = new search_adapter(this, List);
        searchrecyclerview.setAdapter(adapter);
        searchrecyclerview.setItemAnimator(null);

        topicreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Topic E = dataSnapshot.getValue(Topic.class);
                    List.add(E);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchwidget).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }


}
