package com.example.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playGame = findViewById(R.id.playGame);
        Intent goToGameActivity = new Intent(getApplicationContext(), GameActivity.class);
        playGame.setOnClickListener(onClick -> {
            startActivity(goToGameActivity);
        });
    }
}
