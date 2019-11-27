package com.example.cs125finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    /** Background view to change at each event */
    private ImageView view = findViewById(R.id.background);

    /** Button for the first action a player could make */
    private Button actionOne = findViewById(R.id.actionOne);

    /** Button for the second action a player could make. */
    private Button actionTwo = findViewById(R.id.actionTwo);

    /** Text containging the scenario for each specific event */
    private TextView text = findViewById(R.id.text);

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionOne.setOnClickListener(unused -> {
            firstEvent();
        });

        actionTwo.setOnClickListener(unused -> {
            secondEvent();
        });

        text.setText("Stuff and Things");
    }
    public void firstEvent() {
        // Begin another event or something like that
        actionOne.setText("Do Other Stuff");
    }
    public void secondEvent() {
        // Begin an action event or fight or something like that
        actionTwo.setText("Do other stuff again");
    }
    public void thirdEvent() {

    }
    public void fourthEvent() {

    }
}
