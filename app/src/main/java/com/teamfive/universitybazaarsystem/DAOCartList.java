package com.teamfive.universitybazaarsystem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOCartList
{
    private DatabaseReference databaseReference;
    public DAOCartList()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
            databaseReference = db.getReference(CartItems.class.getSimpleName());

    }
    public Task<Void> add(CartItems item)
    {
        return databaseReference.push().setValue(item);

    }
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key)
    {
        if(key == null)
        {
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }

    public Query get()
    {
        return databaseReference.orderByKey();
    }
}
