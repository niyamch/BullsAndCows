package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class LoggedinActivity extends AppCompatActivity {
    Button b_play, b_best, b_stat;
    TextView tv_hello, tv_logout;

    String userId;
    String username;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);

        b_play = (Button) findViewById(R.id.b_play);
        b_best = (Button) findViewById(R.id.b_best);
        b_stat = (Button) findViewById(R.id.b_stat);

        tv_hello = (TextView) findViewById(R.id.tv_hello);
        tv_logout = (TextView) findViewById(R.id.tv_logout);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        DocumentReference dr = fStore.collection("users").document(userId);
        dr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                tv_hello.setText("Hello " + documentSnapshot.getString("username") + "!");
            }
        });

        b_play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityGameChoose();
            }
        });
        b_best.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityBestScores();
            }
        });
        b_stat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityStatistics();
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void openActivityGameChoose(){
        Intent intent = new Intent(this, GameChooseActivity.class);
        startActivity(intent);
    }

    public void openActivityBestScores(){
        Intent intent = new Intent(this, BestScoresActivity.class);
        startActivity(intent);
    }

    public void openActivityStatistics(){
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}