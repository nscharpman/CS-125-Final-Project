package com.example.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button destroyChallen = findViewById(R.id.destroy);
        Button reincarnate = findViewById(R.id.reincarnate);
        ImageView background = findViewById(R.id.background);
    }
}
