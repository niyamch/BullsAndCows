package com.example.bullsandcows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.Var;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.List;

import static com.google.firebase.firestore.Query.Direction.DESCENDING;

public class BestScoresActivity extends AppCompatActivity {

    Button b_easy, b_hard;
    TextView tv_output, tv_dif;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    String userId;

    String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestscores);

        b_easy = (Button) findViewById(R.id.b_easy);
        b_hard = (Button) findViewById(R.id.b_hard);

        tv_dif = (TextView) findViewById(R.id.tv_dif);
        tv_output = (TextView) findViewById(R.id.tv_output);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference dr = fStore.collection("users").document(userId);

        /*b_easy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CollectionReference crEasy = fStore.collection("triesEasy");
                crEasy.whereEqualTo("userId", userId)
                        .orderBy("tries")
                        .limit(10)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int i = 1;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        output = output + i + "." + document.getString("tries") + "tries" + "\n";
                                        Log.d("TAG", document.getId() + " => " + document.getData());
                                    }
                                } else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });
                tv_output.setText(output);
            }
        });
        b_hard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                CollectionReference crHard = fStore.collection("triesHard");
                crHard.whereEqualTo("userId", userId)
                        .orderBy("tries")
                        .limit(10)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int i = 1;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        output = output + i + "." + document.getString("tries") + "tries" + "\n";
                                    Log.d("TAG", document.getId() + " => " + document.getData());

                                }
                                } else {
                                    Log.d("TAG", "Error getting documents: ", task.getException());
                                }
                            }
                        });
                tv_output.setText(output);
            }
        });*/
    }
}