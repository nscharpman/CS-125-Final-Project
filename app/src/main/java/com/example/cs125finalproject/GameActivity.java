package com.example.cs125finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;
import java.util.HashMap;
import java.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GameActivity extends AppCompatActivity {
    /** Background view to change at each event */
    private ImageView view;
    // We need to set the image view for each function that we define. Therefore need multiple picture options.

    /** Button for the first action a player could make */
    private Button actionOne;

    /** Button for the second action a player could make. */
    private Button actionTwo;

    /** Text containing the scenario for each specific event */
    private TextView label;

    private TextView insult;

    private Map<Integer, String> artifacts;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        view = findViewById(R.id.background);
        // We need to set the image view for each function that we define. Therefore need multiple picture options.

        actionOne = findViewById(R.id.actionOne);

        actionTwo = findViewById(R.id.actionTwo);

        label = findViewById(R.id.text);

        insult = findViewById(R.id.insult);

        artifacts = new HashMap<>();

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
        actionOne.setText("Attack with a sword");
        actionTwo.setText("Attack with a knife");
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
        label.setText("That's disgusting, but you have gained the healing charm from his blood. " +
                "You can now move onto the next village or continue exploring");
        artifacts.put(1, "Healing charm");
        actionOne.setText("Move onto next village");
        actionTwo.setText("Continue exploring");
        actionOne.setOnClickListener(unused -> eleventhEvent());
        actionTwo.setOnClickListener(unused -> twelfthEvent());
    }

    public void sixthEvent() {
        label.setText("The village is happy you are protecting them, but not for long. You hear a " +
                "loud bellowing from the forest");
        actionOne.setText("Investigate the forest");
        actionTwo.setText("Let the next passerby deal with it");
        actionOne.setOnClickListener(unused -> thirteenthEvent());
        actionTwo.setOnClickListener(unused -> fourteenthEvent());
    }

    public void seventhEvent() {
        label.setText("You have killed the beast. You can now move onto the next village or continue exploring");
        actionOne.setText("Move onto next village");
        actionTwo.setText("Continue exploring");
        actionOne.setOnClickListener(unused -> fifteenthEvent());
        actionTwo.setOnClickListener(unused -> sixteenthEvent());
    }

    public void eighthEvent() {
        label.setText("I have to figure this one out.");
    }

    public void ninthEvent() {
        label.setText("The beast followed you and killed you upstairs");
    }

    public void tenthEvent() {
        label.setText("The beast's companion was outside waiting. It killed you");
    }

    public void eleventhEvent() {
        label.setText("");
    }

    public void twelfthEvent() {

    }

    public void thirteenthEvent() {

    }

    public void fourteenthEvent() {

    }

    public void fifteenthEvent() {

    }

    public void sixteenthEvent() {

    }

    // Nic:
    // - Work on Trivia API
    // - Alert Dialog
    // - EndGame Alert Dialog (Restart to first event (as positive button))
    //
    // Zakir:
    // - Work on story
    // - Add in map variable stuff
    // - If person contains map variable, add third button.


    public void triviaQuestions(String question) {
        if (question.equals("Computer Questions T/F")) {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://opentdb.com/api.php?amount=1&category=18&type=multiple&encode=base64";
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String stuff) {
                    // Is it possible to decode this before proceeding??
                    byte[] decodedBytes = Base64.getDecoder().decode(stuff);
                    String decodedString = new String(decodedBytes);
                    Gson gson = new Gson();
                    JsonElement element = gson.toJsonTree(decodedString);
                    JsonObject object = element.getAsJsonObject();
                    RiddlerFight(object);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    insult.setText("Fuck");
                }
            });
        } else if (question.equals("Computer Questions Multiple")) {
            RequestQueue queueTwo = Volley.newRequestQueue(this);
        }
    }

    public void RiddlerFight(final JsonObject input) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflater = getLayoutInflater().inflate(R.layout.chunk_triviaquestions_fight,
                null, false);
        RadioGroup presets = inflater.findViewById(R.id.answers);
        JsonArray newInput = input.get("results").getAsJsonArray();
    }

    public void insultGenerator() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://evilinsult.com/generate_insult.php?lang=en&type=json";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override // This needs to be a Json object request
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
