package com.example.giantbombapi.ui;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.giantbombapi.Constants;
import com.example.giantbombapi.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences.Editor mEditor;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.editTextPersonName) EditText my_name;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.go_button) Button go_button;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.favoriteVideos) Button mFavoriteVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();


        go_button.setOnClickListener(this);
        mFavoriteVideos.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit_app) {
            exit_app();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exit_app() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
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
                addToSharedPrefs(username);
                String nonBlankUsername = username.toUpperCase(Locale.ROOT);
                Toast popName = Toast.makeText(MainActivity.this,nonBlankUsername,Toast.LENGTH_LONG);
                popName.setGravity(Gravity.CENTER, 0, -200);
                popName.show();
                Intent intent = new Intent(MainActivity.this, MoviesListActivity.class);
                startActivity(intent);
            }
        }
        else if(view == mFavoriteVideos){
            Intent intent = new Intent(MainActivity.this, SavedVideoListActivity.class);
            startActivity(intent);
        }
    }
    private void addToSharedPrefs(String username) {
        mEditor.putString(Constants.USER_NAME, username).apply(); 
    }
}