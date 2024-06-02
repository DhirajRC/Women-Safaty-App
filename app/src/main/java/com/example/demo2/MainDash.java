package com.example.demo2;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Map;

public class MainDash extends AppCompatActivity {
    TextView txt1;
    ImageButton current_map1,sosjava,helps,youtubepl;
    Location currentLocation;

    String locate;
    FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference myref = firebaseDatabase.getReference().child("User1");
    public static final int RequestPermissionCode = 1;
    private static final int REQUEST_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main_dash);
        current_map1 = findViewById(R.id.current_map);
        helps = findViewById(R.id.help);
        youtubepl = findViewById(R.id.youtub);
        sosjava = findViewById(R.id.sos);
        txt1 = findViewById(R.id.sms);
        Intent intent = getIntent();
        Maps_ maps_ = new Maps_();



        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firestore.collection("Location").document(uid);

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Locationhelper locationHelper = documentSnapshot.toObject(Locationhelper.class);
                    if (locationHelper != null) {
                        double latitude = locationHelper.getLat();
                        double longitude = locationHelper.getLan();
                        String locationUrl = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
//                        sendSms(locationUrl);
                        locate=locationUrl;
                    }
                } else {
                    Toast.makeText(MainDash.this, "Location not found", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainDash.this, "Error fetching location: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        Toast.makeText(this, ""+FirebaseAuth
                .getInstance().getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
        DocumentReference documentReference1 = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());


        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                    UserInfoHolder userInfoHolder  = documentSnapshot.toObject(UserInfoHolder.class);
                 Log.d("AGAAAAAAAAAAAAAAAAA", "onSuccess: "+documentSnapshot.getString("mobileNo"));
                Toast.makeText(MainDash.this, ""+userInfoHolder.getMobileNo(), Toast.LENGTH_SHORT).show();
                txt1.setText(userInfoHolder.getMobileNo());
            }
        });



        youtubepl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent10  =new Intent(MainDash.this,YoutubePlayer.class);
                startActivity(intent10);
            }
        });

        helps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent10  =new Intent(MainDash.this,helpline.class);
                startActivity(intent10);
            }
        });
//        myref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String mobile = snapshot.getValue(String.class);
//                txt1.setText(mobile);
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        sosjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String msg= ;
                String number=txt1.getText().toString();
//                String msg=message.getText().toString();
                try {
                    SmsManager smsManager=SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,locate,null,null);
                    Toast.makeText(getApplicationContext(),"Message Sent",Toast.LENGTH_LONG).show();
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Some fiedls is Empty",Toast.LENGTH_LONG).show();
                }
            }
        });
        current_map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainDash.this,Maps_.class);
                startActivity(intent);
            }
        });
    }

    private void fetch2() {


    }

}