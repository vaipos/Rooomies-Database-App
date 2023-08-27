package com.example.roomie_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class editpopup extends AppCompatActivity {

    ImageButton ImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lists_editpopup);
        ImageButton = findViewById(R.id.back_button);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListsMainpage.class);
                startActivity(intent);
                finish();

            }
        });

        // Find views by their IDs
        RecyclerView recyclerView = findViewById(R.id.edit_list);
        Button saveButton = findViewById(R.id.save);
        ArrayList<String> itemList = new ArrayList<String>();

        // Set a click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        // Save the list to shared preferences
                        SharedPreferences prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        Set<String> itemSet = new HashSet<>(itemList);
                        editor.putStringSet("itemList", itemSet);
                        editor.apply();

                        // Close the activity
                        finish();
                    }
                });

            }
        });
    }
}
