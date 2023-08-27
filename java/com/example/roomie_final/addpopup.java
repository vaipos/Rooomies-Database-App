package com.example.roomie_final;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class addpopup extends Activity {

    private TextView mToiletriesTextView;
    private TextView mStuffTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_lists_popup);

        mToiletriesTextView = findViewById(R.id.toilet_textview);
        mStuffTextView = findViewById(R.id.stuff_textview);

        Button mAddButton = findViewById(R.id.mAdd_Button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toiletries = mToiletriesTextView.getText().toString();
                String stuff = mStuffTextView.getText().toString();
                // Do something with the entered text
                // For example, update a database or display a toast message
                Toast.makeText(addpopup.this, "Toiletries: " + toiletries + "\nStuff: " + stuff, Toast.LENGTH_SHORT).show();
            }
        });
    }
}


