package com.example.roomie_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class editRoomate extends AppCompatActivity {
    Button save;
    TextInputEditText person1;
    ImageButton adder;
    TextInputLayout added;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_roomate_popup);
        adder = findViewById(R.id.adder);
        added = findViewById(R.id.added);
        save = findViewById(R.id.save);
        person1 = findViewById(R.id.person1);
        String text = String.valueOf(person1.getText());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homepage2.class);
                startActivity(intent);
                finish();
            }
        });
        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               added.setVisibility(View.VISIBLE);
            }
        });
    }
}
