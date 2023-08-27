package com.example.roomie_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ChoreAdd extends AppCompatActivity {
    ImageButton ImageButton;
    TextInputEditText editChore;
    FirebaseFirestore fstore;

    FirebaseAuth mAuth;
    String urg;
    String time;
    FirebaseUser user;
    int i;



    Button buttonsave, green, yellow, red, one, week, biW, mon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomies_chores_popup);
        ImageButton = findViewById(R.id.backOut);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        editChore = findViewById(R.id.ChoreName);
        buttonsave = findViewById(R.id.save);
        green = findViewById(R.id.g);
        yellow = findViewById(R.id.y);
        red = findViewById(R.id.r);
        one = findViewById(R.id.oneTime);
        week = findViewById(R.id.weekly);
        biW = findViewById(R.id.biWeekly);
        mon = findViewById(R.id.monthly);
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urg = "Green";
            }
        });
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urg = "Yellow";
            }
        });
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urg = "Red";
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "One Time";
            }
        });
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "Weekly";
            }
        });
        biW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "Bi-Weekly";
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = "Monthly";
            }
        });





        buttonsave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                choreName = String.valueOf(editChore.getText());
                if (TextUtils.isEmpty(choreName)) {
                    Toast.makeText(ChoreAdd.this, "Enter Chore", Toast.LENGTH_SHORT).show();
                    return;
                }
                choreName = String.valueOf(editChore.getText());
                i++;
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Chore", choreName);
                hashMap.put("Urgency", urg);
                hashMap.put("Time", time);
               String userID = mAuth.getCurrentUser().getEmail();
               String choreID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection(userID + " - chores").document(choreName);
                documentReference.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ChoreAdd.this, "Chore Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                        startActivity(intent);
                        finish();
                        setChore(choreName);
                    }
                });

            }


        });


        }
    private String choreName;
    public void setChore(String choreVal) {
        this.choreName = choreVal;
    }
    public String getChore() {
        return choreName;
    }


}

