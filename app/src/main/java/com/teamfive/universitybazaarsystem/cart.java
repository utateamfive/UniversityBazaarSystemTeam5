package com.teamfive.universitybazaarsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class cart extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView cartrecyclerview;
    cartRV cartadapter;
    DAOCartList cartdao;
    String key = null;
    boolean isLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        swipeRefreshLayout = findViewById(R.id.swipeCart);
        cartrecyclerview = findViewById(R.id.cartRV);
        cartrecyclerview.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        cartrecyclerview.setLayoutManager(manager);
        cartadapter = new cartRV(this);
        cartrecyclerview.setAdapter(cartadapter);
        cartdao = new DAOCartList();
        loadData();
        cartrecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        cartdao.get(key).addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                ArrayList<CartItems> items = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren())
                {
                    CartItems item = data.getValue(CartItems.class);
                    item.setKey(data.getKey());
                    items.add(item);
                    key = data.getKey();
                }
                cartadapter.setItems(items);
                cartadapter.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void goPayment(View view)
    {
        Intent intent = new Intent(this, choosePayment.class);
        startActivity(intent);
    }

}