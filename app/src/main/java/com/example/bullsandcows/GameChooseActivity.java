package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameChooseActivity extends AppCompatActivity {

    Button b_easy, b_hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamechoose);

        b_easy = (Button) findViewById(R.id.b_easy);
        b_hard = (Button) findViewById(R.id.b_hard);

        b_easy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityEasyGame();
            }
        });
        b_hard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityHardGame();
            }
        });
    }
    public void openActivityEasyGame(){
        Intent intent = new Intent(this, EasyGameActivity.class);
        startActivity(intent);
    }
    public void openActivityHardGame(){
        Intent intent = new Intent(this, HardGameActivity.class);
        startActivity(intent);
    }
}