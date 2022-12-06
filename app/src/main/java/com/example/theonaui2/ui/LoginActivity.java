package com.example.theonaui2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.theonaui2.R;
import com.example.theonaui2.ui.main.chat.ChatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linkView();

        //Go to Register Activity if the sign up button is clicked
        signUpButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        // Check fields if the login button is clicked
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                //TODO
                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                //Go to Main Activity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    protected void linkView() {
        usernameEditText = (EditText) findViewById(R.id.usernameEditTextL);
        passwordEditText = (EditText) findViewById(R.id.passwordEditTextL);
        loginButton = (Button) findViewById(R.id.loginButtonL);
        signUpButton = (Button) findViewById(R.id.signUPButtonL);
    }
}