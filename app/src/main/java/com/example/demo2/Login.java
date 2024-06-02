package com.example.demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView regi;
    TextView l_email_id, l_pass;
    Button login;
    private FirebaseAuth fireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        this.getSupportActionBar().hide();
        regi = findViewById(R.id.ch_reg);
        l_pass = findViewById(R.id.login_ed_password);
        l_email_id = findViewById(R.id.login_ed_text);
        login = findViewById(R.id.login_tn);
        fireAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoginUser();
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });
    }

    private void LoginUser() {
        String email, pass;
        email = l_email_id.getText().toString();
        pass = l_pass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            l_email_id.setError("Email cannot be empty");
            l_email_id.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            l_pass.setError("Password cannot be empty");
            l_pass.requestFocus();
        } else {
            fireAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainDash.class));
                } else {
                    Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
