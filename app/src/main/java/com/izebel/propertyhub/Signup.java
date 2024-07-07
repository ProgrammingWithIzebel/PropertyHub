package com.izebel.propertyhub;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText et_email, et_password, et_confirm_password;
    Button btn_signup;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_pass);
        et_confirm_password = findViewById(R.id.et_confpass);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String confirmPassword = et_confirm_password.getText().toString().trim();

                if (email.isEmpty()) {
                    et_email.setError("Email is required.");
                    et_email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    et_email.setError("Please enter a valid email address");
                    et_email.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    et_password.setError("Password is required.");
                    et_password.requestFocus();
                    return;
                }
                if (password.length() < 6) { // Adjust as per your password policy
                    et_password.setError("Password should be at least 6 characters long.");
                    et_password.requestFocus();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    et_confirm_password.setError("Passwords do not match.");
                    et_confirm_password.requestFocus();
                    return;
                }

                // Create user in Firebase Realtime Database
                createUser(email, password);
            }
        });
    }

    private void createUser(String email, String password) {
        String userId = mDatabase.push().getKey();
        Map<String, String> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);

        mDatabase.child("Users").child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Sign up success
                    Toast.makeText(Signup.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Signup.this, Login.class));
                    finish(); // Finish signup activity
                } else {
                    // Sign up failed
                    Toast.makeText(Signup.this, "Signup failed. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}