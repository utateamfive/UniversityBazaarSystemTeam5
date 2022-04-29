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

public class LoanActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView loanrecyclerView;
    loanrv loanadapter;
    DAOLoanItems loandao;
    boolean isLoading = false;
    String key = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        swipeRefreshLayout = findViewById(R.id.swipLN);
        loanrecyclerView = findViewById(R.id.loanRV);
        loanrecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        loanrecyclerView.setLayoutManager(manager);
        loanadapter = new loanrv(this);
        loanrecyclerView.setAdapter(loanadapter);
        loandao = new DAOLoanItems();
        loadData();
        loanrecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        loandao.get(key).addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<LoanItems> items = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren())
                {
                    LoanItems item = data.getValue(LoanItems.class);
                    item.setKey(data.getKey());
                    items.add(item);
                    key = data.getKey();
                }
                loanadapter.setItems(items);
                loanadapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addLoan(View view)
    {
        Intent intent = new Intent(this, addLoan.class);
        startActivity(intent);
    }
}