package com.teamfive.universitybazaarsystem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class topicdatabase {
    private DatabaseReference databaseReference;
    public topicdatabase()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Topic.class.getSimpleName());
    }
    public Task<Void> add(Topic topic)
    {
        return databaseReference.push().setValue(topic);
    }
}
