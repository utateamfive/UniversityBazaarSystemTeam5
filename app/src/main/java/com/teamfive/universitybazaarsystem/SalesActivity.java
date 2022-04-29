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

public class SalesActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView salesrecyclerView;
    SalesRVAdapter salesadapter;
    DAOSalesItems salesdao;
    boolean isLoading = false;
    String key = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        swipeRefreshLayout = findViewById(R.id.swip);
        salesrecyclerView = findViewById(R.id.SalesRV);
        salesrecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        salesrecyclerView.setLayoutManager(manager);
        salesadapter = new SalesRVAdapter(this);
        salesrecyclerView.setAdapter(salesadapter);
        salesdao = new DAOSalesItems();
        loadData();
        salesrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int totalItem = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if(totalItem<lastVisible+3)
                {
                    isLoading = true;
                    loadData();
                }
            }
        });
    }

    private void loadData()
    {
        salesdao.get(key).addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<SalesItems> items = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren())
                {
                    SalesItems item = data.getValue(SalesItems.class);
                    item.setKey(data.getKey());
                    items.add(item);
                    key = data.getKey();
                }
                salesadapter.setItems(items);
                salesadapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addSales(View view)
    {
        Intent intent = new Intent(this, addSales.class);
        startActivity(intent);
    }
    public void viewCart(View view)
    {
        Intent intent = new Intent(this, cart.class);
        startActivity(intent);
    }
}