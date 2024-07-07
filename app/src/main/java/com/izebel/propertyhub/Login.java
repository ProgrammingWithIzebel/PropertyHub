package com.izebel.propertyhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText et_email, et_pass;
    TextView tv_forgot;
    Button btn_login, btn_signup;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        tv_forgot = findViewById(R.id.tv_forgot);
        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);

        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Forgotpass.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_pass.getText().toString().trim();

                if (email.isEmpty()) {
                    et_email.setError("Email is required.");
                    et_email.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    et_email.setError("Please enter a valid email address");
                    et_email.requestFocus();
                    return;
                } else if (password.isEmpty()) {
                    et_pass.setError("Password is required.");
                    et_pass.requestFocus();
                    return;
                }

                // Authenticate user with Firebase Realtime Database
                authenticateUser(email, password);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });
    }

    private void authenticateUser(final String email, final String password) {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean userFound = false;

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String dbEmail = userSnapshot.child("email").getValue(String.class);
                    String dbPassword = userSnapshot.child("password").getValue(String.class);

                    if (dbEmail != null && dbEmail.equals(email) && dbPassword != null && dbPassword.equals(password)) {
                        userFound = true;
                        break;
                    }
                }

                if (userFound) {
                    // Sign in success
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    // Sign in failed
                    Toast.makeText(Login.this, "Authentication failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Login.this, "Database error. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
