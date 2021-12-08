package com.example.giantbombapi.ui;


import butterknife.BindView;
import butterknife.ButterKnife;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giantbombapi.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextPersonName) EditText my_name;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.go_button) Button go_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        go_button.setOnClickListener(this);
    }
            @Override
            public void onClick(View view) {
        if(view == go_button){
            String username = my_name.getText().toString();
            if(username.equals("")){
                String feedBackMessage = "type your name".toUpperCase(Locale.ROOT);
                Toast popName = Toast.makeText(MainActivity.this,feedBackMessage,Toast.LENGTH_LONG);
                popName.setGravity(Gravity.CENTER, 0, -200);
                popName.show();
            }
            else{
                String nonBlankUsername = username.toUpperCase(Locale.ROOT);
                Toast popName = Toast.makeText(MainActivity.this,nonBlankUsername,Toast.LENGTH_LONG);
                popName.setGravity(Gravity.CENTER, 0, -200);
                popName.show();
                Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
                intent.putExtra("username",nonBlankUsername);
                startActivity(intent);
            }
        }
            }
    }