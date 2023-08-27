package com.example.roomie_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;
    CheckBox checks;

    ImageButton ImageButton;
    ImageButton homeButton;
    FirebaseFirestore fstore;
    String store;
    RecyclerView recyclerView;
    ArrayList<chore> choreList;
    MyAdapter MyAdapter;
    ImageButton account;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain2);
        homeButton = findViewById(R.id.home);
        ProgressDialog progressDialog = new ProgressDialog(this);
        // progressDialog.setCancelable(false);
        //progressDialog.setMessage("Fetching Data...");
        //progressDialog.show();
        auth = FirebaseAuth.getInstance();

        //button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        account = findViewById(R.id.account);
        user = auth.getCurrentUser();
        String userID = user.getEmail();


        fstore = FirebaseFirestore.getInstance();
        choreList = new ArrayList<chore>();
        MyAdapter = new MyAdapter(MainActivity2.this, choreList);

        //  8tgrecyclerView.setAdapter(MyAdapter);

        EventChangeListener(userID);



        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), RoomiesLogin.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }


/*        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), RoomiesLogin.class);
                startActivity(intent);
                finish();
            }
        });*/

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homepage.class);
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
        ImageButton = findViewById(R.id.addChore);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChoreAdd.class);
                startActivity(intent);
                finish();

            }
        });
    }



    private void EventChangeListener(String userId) {
        String word;
        fstore.collection(userId).orderBy("Chore", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                    /* if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }*/
                            Toast.makeText(MainActivity2.this, "Chore Error", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() ==  DocumentChange.Type.ADDED) {
                                choreList.add(dc.getDocument().toObject(chore.class));
                            }

                            MyAdapter.notifyDataSetChanged();


                        }



                    }
                });
    }

}