package com.example.bullsandcows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button b_log, b_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_log = (Button) findViewById(R.id.b_log);
        b_reg = (Button) findViewById(R.id.b_reg);
        b_log.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityLogin();
            }
        });
        b_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openActivityRegister();
            }
        });
    }

    public void openActivityLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openActivityRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}