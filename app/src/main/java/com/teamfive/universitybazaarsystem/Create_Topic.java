package com.teamfive.universitybazaarsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Create_Topic extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] Category_list = {"Tutoring", "Event", "Announcement", "Jobs"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_topic);
        Spinner select = (Spinner) findViewById(R.id.Category_select);
        select.setOnItemSelectedListener(this);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Category_list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select.setAdapter(ad);
        final EditText title = findViewById(R.id.topic_name);
        final Spinner category = findViewById(R.id.Category_select);
        final EditText author = findViewById(R.id.topic_author);
        final EditText body = findViewById(R.id.body_text);
        Button submit = findViewById(R.id.submit);
        topicdatabase db = new topicdatabase();
        submit.setOnClickListener(v ->
        {
            Topic topic = new Topic(title.getText().toString(), category.getSelectedItem().toString(), author.getText().toString(), body.getText().toString());
            db.add(topic).addOnSuccessListener(suc ->
                    Toast.makeText(this, "Topic added", Toast.LENGTH_SHORT).show()).addOnFailureListener(er ->
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show()
            );
            finish();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), Category_list[position], Toast.LENGTH_SHORT).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }
}