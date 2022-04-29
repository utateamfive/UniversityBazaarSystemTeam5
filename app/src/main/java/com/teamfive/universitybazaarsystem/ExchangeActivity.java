package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ExchangeActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView exchangerecyclerView;
    exchangerv exchangeadapter;
    DAOExchangeItems exchangedao;
    boolean isLoading = false;
    String key = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        swipeRefreshLayout = findViewById(R.id.swipEX);
        exchangerecyclerView = findViewById(R.id.exchangeRV);
        exchangerecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        exchangerecyclerView.setLayoutManager(manager);
        exchangeadapter = new exchangerv(this);
        exchangerecyclerView.setAdapter(exchangeadapter);
        exchangedao = new DAOExchangeItems();
        loadData();
        exchangerecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem<lastVisible+3)
                {
                    isLoading=true;
                    loadData();
                }
            }
        });
    }
    private void loadData()
    {
        exchangedao.get(key).addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<ExchangeItems> items = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren())
                {
                    ExchangeItems item = data.getValue(ExchangeItems.class);
                    item.setKey(data.getKey());
                    items.add(item);
                    key = data.getKey();
                }
                exchangeadapter.setItems(items);
                exchangeadapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void addExchange(View view)
    {
        Intent intent = new Intent(this, addExchange.class);
        startActivity(intent);
    }
}