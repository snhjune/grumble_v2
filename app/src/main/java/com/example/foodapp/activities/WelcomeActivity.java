package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.foodapp.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * If user clicks register button, the app will locate user to registeration page
     * @param view
     */
    public void register(View view){
        startActivity(new Intent(WelcomeActivity.this, RegistrationActivity.class));

    }

    /**
     * if user taps on login button, the app will locate user to login page
     * @param view
     */
    public void login(View view){
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
    }
}