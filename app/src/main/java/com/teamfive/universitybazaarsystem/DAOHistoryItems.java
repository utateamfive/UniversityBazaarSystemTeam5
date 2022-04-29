package com.teamfive.universitybazaarsystem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOHistoryItems {
    private DatabaseReference databaseReference;
    public DAOHistoryItems()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(HistoryItems.class.getSimpleName());
    }
    public Task<Void> add(HistoryItems item)
    {
        return databaseReference.push().setValue(item);

    }
    public Query get()
    {
        return databaseReference.orderByKey();
    }
}
