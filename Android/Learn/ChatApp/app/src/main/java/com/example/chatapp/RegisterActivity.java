package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    // Widgets
    EditText edtUsername, edtEmail, edtPass, edtConfirmPass;
    Button btnRegister;
    TextView tvToLogin;

    // Firebase
    FirebaseAuth auth;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initializing Widgets:
        mapping();


        // Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Adding Event Listener to Button Register
        btnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                String username_text    = edtUsername.getText().toString();
                String email_text       = edtEmail.getText().toString();
                String pass_text        = edtPass.getText().toString();
                String cfPass_text      = edtConfirmPass.getText().toString();

                if (TextUtils.isEmpty(username_text) || TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(RegisterActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                } else if (!pass_text.equals(cfPass_text)){
                    Toast.makeText(RegisterActivity.this, "Please fill the same password", Toast.LENGTH_SHORT).show();
                } else{
                    RegisterNow(username_text, email_text , pass_text);
                }
            }
        });

        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void mapping() {
        edtUsername     = (EditText) findViewById(R.id.edtUsername);
        edtEmail        = (EditText) findViewById(R.id.edtEmail);
        edtPass         = (EditText) findViewById(R.id.edtPassword);
        edtConfirmPass  = (EditText) findViewById(R.id.edtConfrimPassword);
        tvToLogin       = (TextView) findViewById(R.id.tvToLogin);
        btnRegister     = (Button) findViewById(R.id.btnRegister);
    }

    private void RegisterNow(final String username, String email, String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            myRef = FirebaseDatabase.getInstance().getReference("MyUsers")
                                    .child(userid);

                            // HashMaps
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("status" , "offline");

                            // Opening the Main Activity after Success Registration
                            myRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                        } else{
                            Toast.makeText(RegisterActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

