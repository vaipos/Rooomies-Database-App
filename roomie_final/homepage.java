package com.example.roomie_final;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {

    FirebaseAuth auth;
    ImageButton add;
    FirebaseUser user;
    TextView emailDisplay;
    ImageButton homeButton;
    ImageButton choresButton;
    ImageButton sidepop;
    ImageButton account;
    ImageButton list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        emailDisplay = findViewById(R.id.email);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String userID = user.getEmail();
        homeButton = findViewById(R.id.homeButton);
        choresButton = findViewById(R.id.chores);
        sidepop = findViewById(R.id.sidebar);
        account = findViewById(R.id.account);
        add = findViewById(R.id.add);
        list = findViewById(R.id.lists);

        emailDisplay.setText(userID);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homepage.class);
                startActivity(intent);
                finish();
            }
        });
        choresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        sidepop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sidePopUp.class);
                startActivity(intent);
                finish();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), account.class);
                startActivity(intent);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), editRoomate.class);
                startActivity(intent);
                finish();
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListsMainpage.class);
                startActivity(intent);
                finish();
            }
        });




    }
}
