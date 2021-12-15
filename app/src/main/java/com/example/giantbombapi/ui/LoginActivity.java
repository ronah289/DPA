package com.example.giantbombapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giantbombapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextEmailAddress) EditText emailAddress;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextPassword) EditText password;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_button) Button loginButton;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signupText) TextView signUpText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        signUpText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == signUpText) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
    }
}