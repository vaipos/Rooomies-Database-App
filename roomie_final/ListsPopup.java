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

public class ListsPopup extends AppCompatActivity {
    ImageButton ImageButton;

    TextInputEditText newList;
    TextInputEditText newTitle;
    FirebaseFirestore fstore;

    FirebaseAuth mAuth;

    FirebaseUser user;
    int i;



    Button buttoncreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists_popup);
        ImageButton = findViewById(R.id.exit_button);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListsMainpage.class);
                startActivity(intent);
                finish();

            }
        });
        newList = findViewById(R.id.listContent);
        newTitle = findViewById(R.id.ListName);
        buttoncreate = findViewById(R.id.create);
        fstore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();






        buttoncreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

              listContent = String.valueOf(newList.getText());
                String ListName = String.valueOf(newTitle.getText());
                if (TextUtils.isEmpty(listContent)) {
                    Toast.makeText(ListsPopup.this, "Enter List", Toast.LENGTH_SHORT).show();
                    return;
                }
                listContent = String.valueOf(newList.getText());
                i++;
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("List", listContent);
                String userID = mAuth.getCurrentUser().getEmail();
                String listID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection(userID + " - lists").document(ListName);
                documentReference.set(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ListsPopup.this, "List Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ListsMainpage2.class);
                        startActivity(intent);
                        finish();
                        setList(listContent);
                    }
                });

            }


        });


    }
    private String listContent;
    public void setList(String listVal) {
        this.listContent = listVal;
    }
    public String getList() {
        return listContent;
    }


}