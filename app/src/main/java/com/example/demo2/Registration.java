package com.example.demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    EditText email_j,password_j,l_fname,l_f_adess,l_mobile;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    Button btn_reg;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("User"+1);
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        email_j = findViewById(R.id.Email);
        password_j = findViewById(R.id.password);
        btn_reg = findViewById(R.id.reg_btn);
        l_f_adess=findViewById(R.id.F_address);
        l_fname=findViewById(R.id.editText_Fname);
        l_mobile = findViewById(R.id.M_mobile);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = l_mobile.getText().toString();
                root.setValue(mobile);
                createUser();
            }
        });
    }
    public void createUser()
    {
        String email, pass,mobile,full_Name,Full_Adress;
        email= email_j.getText().toString();
        pass = password_j.getText().toString();
        mobile= l_mobile.getText().toString();
        full_Name=l_fname.getText().toString();
        Full_Adress=l_f_adess.getText().toString();



        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Enter Email Id", Toast.LENGTH_SHORT).show();
//            return;
        }
        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
//            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(Registration.this, "Successful", Toast.LENGTH_SHORT).show();
                    userID=firebaseAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = firebaseFirestore.collection("Users").document(userID);
                    Map<String,Object> user = new HashMap<>();
                    user.put("fullName", full_Name);
                    user.put("mobileNo", mobile);
                    user.put("fullAdress", Full_Adress);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Log.d("TAG", "Success"+userID);
                            Intent intent = new Intent(Registration.this, Login.class);
                            startActivity(intent);

                            String str = l_mobile.getText().toString();
                            Intent intent2 = new Intent(Registration.this,MainDash.class);
                            intent2.putExtra("m_no", str);
                        }
                    });
                }
                else
                {
                    Toast.makeText(Registration.this, "Faild", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}