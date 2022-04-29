package com.teamfive.universitybazaarsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class view_delete_topic extends AppCompatActivity {
    DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
    EditText view_name;
    EditText view_author;
    EditText view_category;
    EditText view_bodytext;
    private Button edit;
    private Button delete;
    private Button message;
    String text1;
    String text2;
    String text3;
    String text4;
    String Key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_delete_topic);
        edit = findViewById(R.id.editbutton);
        delete = findViewById(R.id.deletebutton);
        message = findViewById(R.id.messagebutton);
        view_name = findViewById(R.id.blowup_title);
        view_author = findViewById(R.id.blowup_author);
        view_category = findViewById(R.id.blowup_category);
        view_bodytext = findViewById(R.id.blowup_bodytext);
        delete.setOnClickListener(v -> deleteclick(v));

        //Free this block once communications integrated
       /*message.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                 openCommunications();
            }

*/
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            text1 = extras.getString("title");
            text2 = extras.getString("category");
            text3 = extras.getString("author");
            text4 = extras.getString("bodytext");
            Key = extras.getString("Key");
            view_name.setText(text1);
            view_author.setText(text2);
            view_category.setText(text3);
            view_bodytext.setText(text4);
        }
        edit.setOnClickListener(view -> {
            final String update_name = view_name.getText().toString();
            final String update_author = view_author.getText().toString();
            final String update_category = view_category.getText().toString();
            final String update_bodytext = view_bodytext.getText().toString();
            reference.child("Topic").child(Key).child("topicname").setValue(update_name);
            reference.child("Topic").child(Key).child("topicAuthor").setValue(update_author);
            reference.child("Topic").child(Key).child("topiccategory").setValue(update_category);
            reference.child("Topic").child(Key).child("bodytext").setValue(update_bodytext);

        });

/*

        private void openCommunications() {
            Intent opencomm = new Intent(this, );//Add communications activity here
            startActivity(opencomm);
        }
    }

 */
    }
        private void deleteclick(View view)
        {
            reference.child("Topic").child(Key).removeValue();
        }
}