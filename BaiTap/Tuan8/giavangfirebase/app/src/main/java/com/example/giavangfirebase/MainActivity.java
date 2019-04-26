package com.example.giavangfirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    EditText txt;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        writeNewPost("12345", "pro7991", "What your name?", "My name is Luong");

        updateName("1234", "Nguyễn Văn A");

        txt = findViewById(R.id.editTextValue);
        btn = findViewById(R.id.buttonSave);

        // Write ma message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("XE");
        database.getReference().child("TEST").setValue("1234");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String a = dataSnapshot.getValue().toString();
                txt.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue(txt.getText().toString());
            }
        });
    }

    private void writeNewUser(String userId, String name, String email){
        User user = new User(name, email);
        mDatabase.child("user").child(userId).setValue(user);
    }
    private void updateName(String userId, String name){
        mDatabase.child("user").child(userId).child("username").setValue(name);
    }

    private void writeNewPost(String uid, String username, String title, String body){
        String key = mDatabase.child("posts").push().getKey();          //Lay duoc key
        Log.e("key: ", key);
        Post post = new Post(uid, username, title, body);
        Map<String, Object> postValue = post.toMap();
//
        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put("/posts1/" + key, "fssdfdfsf");
//        childUpdate.put("/user-posts/" + uid + "/" + key, postValue);
//
        mDatabase.updateChildren(childUpdate);
    }
}
