package com.example.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class FlashActivity extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (FirebaseAuth.getInstance().getCurrentUser() != null){

                    startActivity(new Intent(FlashActivity.this, MainDash.class));
                }else {
                    startActivity(new Intent(FlashActivity.this, Login.class));

                }

            }
        },2000);


    }
}