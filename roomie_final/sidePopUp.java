package com.example.roomie_final;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sidePopUp extends AppCompatActivity {
    Button back;
    Button logout;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView userName;
    TextView account;
    TextView appear;
    TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_pop);
        userName = findViewById(R.id.userName);
        back = findViewById(R.id.back);
        account = findViewById(R.id.account);
        about = findViewById(R.id.about);
        appear = findViewById(R.id.appear);
        logout = findViewById(R.id.logout);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        String userID = user.getEmail();
        userName.setText(userID);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homepage.class);
                startActivity(intent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), RoomiesLogin.class);
                startActivity(intent);
                finish();
            }
        });
        appear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), appear.class);
                startActivity(intent);
                finish();
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), about.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
