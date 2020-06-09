package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class StatisticsActivity extends AppCompatActivity {

    Button b_easy, b_hard;
    TextView tv_output, tv_dif;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    String userId;

    String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        b_easy = (Button) findViewById(R.id.b_easy);
        b_hard = (Button) findViewById(R.id.b_hard);

        tv_dif = (TextView) findViewById(R.id.tv_dif);
        tv_output = (TextView) findViewById(R.id.tv_output);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
    }
}