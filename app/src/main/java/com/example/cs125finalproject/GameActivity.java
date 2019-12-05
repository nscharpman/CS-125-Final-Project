package com.example.cs125finalproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class GameActivity extends AppCompatActivity {
    /** Background view to change at each event */
    private ImageView view = findViewById(R.id.background);
    // We need to set the image view for each function that we define. Therefore need multiple picture options.

    /** Button for the first action a player could make */
    private Button actionOne = findViewById(R.id.actionOne);

    /** Button for the second action a player could make. */
    private Button actionTwo = findViewById(R.id.actionTwo);

    /** Text containing the scenario for each specific event */
    private TextView label = findViewById(R.id.text);

    private TextView insult = findViewById(R.id.insult);

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        label.setText("Stuff and Things");

        actionOne.setOnClickListener(unused -> firstEvent());
        actionTwo.setOnClickListener(unused -> secondEvent());
    }

    public void firstEvent() {
        // Begin another event or something like that
        // Image view of person running away.
        label.setText("You ran like a bitch");
        actionOne.setText("Go back and kill the beast");
        actionTwo.setText("Keep running and find shelter");
        actionOne.setOnClickListener(unused -> thirdEvent());
        actionTwo.setOnClickListener(unused -> fourthEvent());
    }

    public void secondEvent() {
        // Begin an action event or fight or something like that
        // Image of person standing with a sword.
        label.setText("You stayed and fought the beast");
        actionOne.setText("Eat the guts of the beast");
        actionTwo.setText("Move on to the next village");
        actionOne.setOnClickListener(unused -> fifthEvent());
        actionTwo.setOnClickListener(unused -> sixthEvent());
    }

    public void thirdEvent() {
        label.setText("You went back for the beast. Kill him with either a sword or a knife");
        actionOne.setText("Kill with a sword");
        actionTwo.setText("Kill with a knife");
        actionOne.setOnClickListener(unused -> seventhEvent());
        actionTwo.setOnClickListener(unused -> eighthEvent());
    }

    public void fourthEvent() {
        label.setText("You have found shelter in an abandoned house. You hear a noise upstairs");
        actionOne.setText("Investigate upstairs");
        actionTwo.setText("Leave the house and keep searching for your next mission");
        actionOne.setOnClickListener(unused -> ninthEvent());
        actionTwo.setOnClickListener(unused -> tenthEvent());
    }

    public void fifthEvent() {
        label.setText("That's disgusting. You die from poisoning");
    }

    public void sixthEvent() {
        label.setText("The village is happy you are protecting them");
    }

    public void seventhEvent() {
        label.setText("The beasts armor is too strong to kill with a sword. The beast killed you");
    }

    public void eighthEvent() {
        label.setText("A knife... really? You tried to kill a beast with a knife? Yeah... you're dead");
    }

    public void ninthEvent() {
        label.setText("The beast followed you and killed you upstairs");
    }

    public void tenthEvent() {
        label.setText("The beast's companion was outside waiting. It killed you");
    }

    //maybe add an animation using library
    //maybe add a riddle for the user to solve
    //WebAPI to send random facts per answer from the user.
    // maybe add an animation using library
    // maybe add a riddle for the user to solve
    // WebAPI to send random facts per answer from the user.
    // Make a map variable to store items the users collect within the game

    // Things to do
    // - Fix the emulator
    // - start the APIS
    // - figure out how to set the image
    // - also finding images (use API that gives images for the story)
    // - get the story down

    public void webAPICaller(int number) {
        if (number == 1) {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="https://evilinsult.com/generate_insult.php?lang=en&type=json";
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    insult.setText(response.substring(0, 500));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    insult.setText("Fuck");
                }
            });
            queue.add(stringRequest);
        }
    }
}
