package com.example.giantbombapi.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giantbombapi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextEmailAddress) EditText emailAddress;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextPassword) EditText passwordField;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.login_button) Button loginButton;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.signupText) TextView signUpText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        signUpText.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };

    }

    @Override
    public void onClick(View view) {
        if (view == signUpText) {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
            finish();
        }
        if (view == loginButton) {
            login();
        }
    }

    private void login() {
        String email = emailAddress.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        if (email.equals("")) {
            emailAddress.setError("Enter your email");
            return;
        }
        if (password.equals("")) {
            passwordField.setError("Blank Password");
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    Log.d("login method", "sign in success" + task.isSuccessful());
                    if (!task.isSuccessful()) {
                        Log.w("login method",task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}