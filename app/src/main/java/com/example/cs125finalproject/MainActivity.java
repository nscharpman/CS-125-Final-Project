package com.example.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playGame = findViewById(R.id.playGame);
        final Intent goToGameActivity = new Intent(getApplicationContext(), GameActivity.class);
        playGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                startActivity(goToGameActivity);
            }
        });
        finish();
    }
}
