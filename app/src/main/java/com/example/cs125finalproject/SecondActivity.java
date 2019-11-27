package com.example.cs125finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    /** Background view to change at each event */
    private ImageView view = findViewById(R.id.background);

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button actionOne = findViewById(R.id.actionOne);
        Button actionTwo = findViewById(R.id.actionTwo);
        TextView initialText = findViewById(R.id.firstEvent);

        actionOne.setOnClickListener(unused -> {
            firstEvent();
        });

        actionTwo.setOnClickListener(unused -> {
            secondEvent();
        });

        initialText.setText("Stuff and Things");
    }
    public void firstEvent() {
        // Begin another event or something like that
    }
    public void secondEvent() {
        // Begin an action event or fight or something like that
    }
}
