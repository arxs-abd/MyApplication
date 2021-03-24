package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText email;
    EditText password;
    Button signUp;
    CheckBox showPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Inisialisasi
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.SignUp);
        auth = FirebaseAuth.getInstance();

        showPassword = findViewById(R.id.showPassword);
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPassword.isChecked()) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else if (!showPassword.isChecked()) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Email or Password cant be Empty", Toast.LENGTH_SHORT).show();
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email or Password cant be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Sign In Successfully", Toast.LENGTH_SHORT).show();
                                Intent a = new Intent(SignUp.this, MainActivity.class);
                                startActivity(a);
                            } else {
                                Toast.makeText(getApplicationContext(), "Sign In Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });

    }
}