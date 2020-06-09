package com.example.bullsandcows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EasyGameActivity extends AppCompatActivity {
    Button b_minus_a, b_minus_b, b_minus_c,
            b_plus_a, b_plus_b, b_plus_c;

    Button b_check, b_resign, b_reset;
    TextView tv_number_a, tv_number_b, tv_number_c;
    TextView tv_info, tv_output;

    Random r;

    int guessA = 0, guessB = 0, guessC = 0;
    int generatedA, generatedB, generatedC;
    int bulls=0, cows=0;
    int tries=0;

    String output="";
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easygame);
        b_minus_a = (Button) findViewById(R.id.b_minus_a);
        b_minus_b = (Button) findViewById(R.id.b_minus_b);
        b_minus_c = (Button) findViewById(R.id.b_minus_c);
        b_plus_a = (Button) findViewById(R.id.b_plus_a);
        b_plus_b = (Button) findViewById(R.id.b_plus_b);
        b_plus_c = (Button) findViewById(R.id.b_plus_c);

        b_check = (Button) findViewById(R.id.b_check);
        b_resign = (Button) findViewById(R.id.b_resign);
        b_reset = (Button) findViewById(R.id.b_reset);

        tv_number_a = (TextView) findViewById(R.id.tv_number_a);
        tv_number_b = (TextView) findViewById(R.id.tv_number_b);
        tv_number_c = (TextView) findViewById(R.id.tv_number_c);

        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_output = (TextView) findViewById(R.id.tv_output);

        r = new Random();
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        generateNumber();

        b_minus_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guessA>0) guessA--;
                tv_number_a.setText("" + guessA);
            }
        });
        b_minus_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guessB>0) guessB--;
                tv_number_b.setText("" + guessB);
            }
        });
        b_minus_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guessC>0) guessC--;
                tv_number_c.setText("" + guessC);
            }
        });

        b_plus_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guessA<9) guessA++;
                tv_number_a.setText("" + guessA);
            }
        });
        b_plus_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guessB<9) guessB++;
                tv_number_b.setText("" + guessB);
            }
        });
        b_plus_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(guessC<9) guessC++;
                tv_number_c.setText("" + guessC);
            }
        });

        b_resign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_resign.setEnabled(false);
                b_check.setEnabled(false);

                tv_info.setText("You lost! The right number is: " + generatedA + " " + generatedB + " " + generatedC );
            }
        });
        b_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                b_resign.setEnabled(true);
                b_check.setEnabled(true);
                generateNumber();
                guessA = 0; guessB = 0; guessC = 0;
                tv_info.setText("");
                tv_number_a.setText("0");
                tv_number_b.setText("0");
                tv_number_c.setText("0");
                tv_output.setText("");
                output = "";
                tries = 0;
            }
        });

        b_check.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(guessA==guessB||guessA==guessC||guessB==guessC)
                    tv_info.setText("Wrong input! All numbers must be different!");
                else{
                    tries++;
                    checkNumber();
                    checkWin();
                    bulls = 0;
                    cows = 0;
                }
            }
        });
    }

    private void checkWin(){
        if(bulls == 3){
            b_resign.setEnabled(false);
            b_check.setEnabled(false);
            tv_info.setText("You won in " + tries + " tries!");
            Map<String,Object> triesObj = new HashMap<>();
            userId = fAuth.getCurrentUser().getUid();
            triesObj.put("userId", userId);
            triesObj.put("tries", tries);
            fStore.collection("triesEasy").add(triesObj).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("TAG", "OnSuccess: DocumentSnapshot added with ID: " + documentReference.getId());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("TAG", "onFailure: " + e.toString());
                }
            });
        }
    }

    private void generateNumber(){
        generatedA = r.nextInt(10);

        do{
            generatedB = r.nextInt(10);
        }while(generatedA==generatedB);

        do{
            generatedC = r.nextInt(10);
        }while(generatedC==generatedA||generatedC==generatedB);

    }

    private void checkNumber(){
        //bulls
        if(guessA==generatedA) bulls++;
        if(guessB==generatedB) bulls++;
        if(guessC==generatedC) bulls++;

        //cows
        if(guessA==generatedB||guessA==generatedC) cows++;
        if(guessB==generatedA||guessB==generatedC) cows++;
        if(guessC==generatedA||guessC==generatedB) cows++;

        output=output+ " " + tries + ". " + guessA + " " + guessB + " " + guessC +
                " - Bulls: " + bulls + ", Cows: " + cows + "\n";
        tv_output.setText(output);
    }

    public void restartActivity(View view) {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}