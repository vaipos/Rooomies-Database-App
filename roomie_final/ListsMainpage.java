package com.example.roomie_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListsMainpage extends AppCompatActivity {

    FirebaseAuth auth;

    Button button;

    ImageButton ImageButton;

    ImageButton imageButton;

    TextView textView;
    FirebaseUser user;
    FirebaseFirestore fstore;
    RecyclerView recyclerView;
    ArrayList<List> mainList;
    MyAdapter MyAdapter;
    ImageButton Chore;
    ImageButton Home;
    ImageButton account;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists_mainpage);
        Chore = findViewById(R.id.choreMove);
        Home = findViewById(R.id.homeMove);
        account = findViewById(R.id.accountMove);
        auth = FirebaseAuth.getInstance();

        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        String userID = user.getEmail();

        fstore = FirebaseFirestore.getInstance();
        mainList = new ArrayList<List>();


        EventChangeListener(userID);


        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), RoomiesLogin.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }

/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), RoomiesLogin.class);
                startActivity(intent);
                finish();
            }
        });*/
        ImageButton = findViewById(R.id.createListButton);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListsPopup.class);
                startActivity(intent);
                finish();

            }
        });
        ImageButton = findViewById(R.id.edit_button);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), editpopup.class);
                startActivity(intent);
                finish();
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homepage.class);
                startActivity(intent);
                finish();
            }
        });
        Chore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
    }

    private void EventChangeListener(String userId) {
        fstore.collection(userId).orderBy("List", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Toast.makeText(ListsMainpage.this, "List Error", Toast.LENGTH_SHORT).show();
                            return;
                        }


                    }
                });
    }



}
