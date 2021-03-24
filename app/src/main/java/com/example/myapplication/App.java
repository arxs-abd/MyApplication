package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class App extends AppCompatActivity {
    Button logOut;
    Button changePassword;
    EditText password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        auth = FirebaseAuth.getInstance();

        logOut = findViewById(R.id.ButtonLogout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(getApplicationContext(), "Logging Out ...", Toast.LENGTH_SHORT);
                Intent a = new Intent(App.this, MainActivity.class);
                startActivity(a);
            }
        });

        password = findViewById(R.id.changePasswordText);
        changePassword = findViewById(R.id.changePassword);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password cant be Empty", Toast.LENGTH_SHORT).show();
                } else {
                    auth.getCurrentUser().updatePassword(password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Password Has Been Changed", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Password Not Changed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}