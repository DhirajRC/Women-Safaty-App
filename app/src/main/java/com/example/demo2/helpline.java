package com.example.demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class helpline extends AppCompatActivity {

    TextView policej,ambulancej,childj,womenj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);
        policej = findViewById(R.id.police);
        ambulancej = findViewById(R.id.Ambulance);
        childj = findViewById(R.id.Child);
        womenj = findViewById(R.id.women);

        policej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number="9834866811";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });
        childj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number="9359747212";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });
        ambulancej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number="7083785319";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });
        womenj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number="7020125415";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                startActivity(callIntent);
            }
        });
    }
}