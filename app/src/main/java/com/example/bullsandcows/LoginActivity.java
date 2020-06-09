package com.example.bullsandcows;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity  {
    EditText et_email, et_pass;
    Button b_login;
    TextView tv_reg;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_email = (EditText) findViewById(R.id.et_email);
        et_pass = (EditText) findViewById(R.id.et_pass);

        b_login = (Button) findViewById(R.id.b_log);

        tv_reg = (TextView) findViewById(R.id.tv_reg);

        fAuth = FirebaseAuth.getInstance();

        b_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_pass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    et_email.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    et_pass.setError("Password is required!");
                    return;
                }

                if(password.length()<8){
                    et_pass.setError("Password must be at least 8 symbols!");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logged in Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoggedinActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

        });

        tv_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }

        });
    }

}