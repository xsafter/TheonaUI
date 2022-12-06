package com.example.theonaui2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.theonaui2.R;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    EditText repeatPasswordEditText;

    Button signUpButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        linkView();

        //Go to Login Activity if the login button is clicked
        loginButton.setOnClickListener(
                v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        //Check fields if the sign up button is clicked
        signUpButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String repeatPassword = repeatPasswordEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(repeatPassword)) {
                Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            }
            else {
                if (password.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    //TODO
                    Toast.makeText(RegisterActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    //Go to Main Activity
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                }
            }
        });
    }

    protected void linkView() {
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        repeatPasswordEditText = (EditText) findViewById(R.id.repeatPasswordEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        loginButton = (Button) findViewById(R.id.loginButton);
    }
}