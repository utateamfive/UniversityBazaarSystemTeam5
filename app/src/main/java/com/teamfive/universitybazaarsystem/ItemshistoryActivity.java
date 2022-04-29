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

public class ItemshistoryActivity extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView historyrecyclerView;
    historyrv historyadapter;
    DAOHistoryItems historydao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemshistory);
        swipeRefreshLayout = findViewById(R.id.swipH);
        historyrecyclerView = findViewById(R.id.historyRV);
        historyrecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        historyrecyclerView.setLayoutManager(manager);
        historyadapter = new historyrv(this);
        historyrecyclerView.setAdapter(historyadapter);
        historydao = new DAOHistoryItems();
        loadData();
    }

    private void loadData()
    {
        historydao.get().addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<HistoryItems> items = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren())
                {
                    HistoryItems item = data.getValue(HistoryItems.class);
                    items.add(item);
                }
                historyadapter.setItems(items);
                historyadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void returnSales(View view)
    {
        Intent intent = new Intent(this, SalesMain.class);
        startActivity(intent);
    }
}
