package com.example.cs125finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    /** Background view to change at each event */
    private ImageView view = findViewById(R.id.background);
    // We need to set the image view for each function that we define. Therefore need multiple picture options.

    /** Button for the first action a player could make */
    private Button actionOne = findViewById(R.id.actionOne);

    /** Button for the second action a player could make. */
    private Button actionTwo = findViewById(R.id.actionTwo);

    /** Text containing the scenario for each specific event */
    private TextView text = findViewById(R.id.text);

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        text.setText("Stuff and Things");

        actionOne.setOnClickListener(unused -> firstEvent());
        actionTwo.setOnClickListener(unused -> secondEvent());
    }

    public void firstEvent() {
        // Begin another event or something like that
        //Image view of person running away.
        text.setText("You ran like a bitch");
        actionOne.setText("Go back and kill the beast");
        actionTwo.setText("Keep running and find shelter");
        actionOne.setOnClickListener(unused -> thirdEvent());
        actionTwo.setOnClickListener(unused -> fourthEvent());
    }

    public void secondEvent() {
        // Begin an action event or fight or something like that
        //Image of person standing with a sword.
        text.setText("You stayed and fought the beast");
        actionOne.setText("Eat the guts of the beast");
        actionTwo.setText("Move on to the next village");
        actionOne.setOnClickListener(unused -> fifthEvent());
        actionTwo.setOnClickListener(unused -> sixthEvent());
    }

    public void thirdEvent() {
        text.setText("You went back for the beast. Kill him with either a sword or a knife");
        actionOne.setText("Kill with a sword");
        actionTwo.setText("Kill with a knife");
        actionOne.setOnClickListener(unused -> seventhEvent());
        actionTwo.setOnClickListener(unused -> eigthEvent());
    }

    public void fourthEvent() {
        text.setText("You have found shelter in an abandoned house. You hear a noise upstairs.");
        actionOne.setText("Investigate upstairs.");
        actionTwo.setText("Leave the house and keep searching for your next mission");
        actionOne.setOnClickListener(unused -> ninthEvent());
        actionTwo.setOnClickListener(unused -> tenthEvent());
    }

    public void fifthEvent() {
        text.setText("That's disgusting. You die from poisoning.");

    }

    public void sixthEvent() {

    }

    public void seventhEvent() {

    }

    public void eigthEvent() {

    }

    public void ninthEvent() {

    }

    public void tenthEvent() {

    }
}
