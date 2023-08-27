package com.example.roomie_final;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RoomiesRegister extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword, confirmPassword;
    FirebaseAuth mAuth;

    Button buttonReg;
    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomies_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm);
        buttonReg = findViewById(R.id.btn_register);

        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RoomiesLogin.class);
                startActivity(intent);
                finish();

            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password, confirmation;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                confirmation = String.valueOf(confirmPassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RoomiesRegister.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RoomiesRegister.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmation)) {
                    Toast.makeText(RoomiesRegister.this, "Enter confirmation Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(RoomiesRegister.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), RoomiesLogin.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RoomiesRegister.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        }

    }
