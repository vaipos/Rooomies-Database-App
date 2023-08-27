package com.example.roomie_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class account extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    ImageButton home;
    TextView username;
    ImageButton chore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountpage);
        username = findViewById(R.id.userName);
        auth = auth.getInstance();
        user = auth.getCurrentUser();
        String userID = user.getEmail();
        username.setText(userID);
        home = findViewById(R.id.home);
        chore = findViewById(R.id.chores);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homepage.class);
                startActivity(intent);
                finish();
            }
        });
        chore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
