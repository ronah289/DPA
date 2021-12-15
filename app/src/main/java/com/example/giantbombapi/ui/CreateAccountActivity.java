package com.example.giantbombapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giantbombapi.R;
import com.google.firebase.auth.FirebaseAuth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextPersonName) EditText personName;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextEmailAddress) EditText emailAddress;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextPassword1) EditText password1;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextPassword2) EditText password2;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.createAccount) Button createAccount;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.go_to_login) TextView goToLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ButterKnife.bind(this);
        createAccount.setOnClickListener(this);
        goToLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {

        if (view == goToLogin) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == createAccount) {
            createNewUser();
        }

    }
    private void createNewUser() {
        final String name = personName.getText().toString().trim();
        final String email = emailAddress.getText().toString().trim();
        String password = password1.getText().toString().trim();
        String confirmPassword = password2.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("auth", "Authentication successful");
                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
