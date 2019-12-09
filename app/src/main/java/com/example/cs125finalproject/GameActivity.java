package com.example.cs125finalproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;
import java.util.HashMap;
import java.util.Base64;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameActivity extends AppCompatActivity {
    /** Background view to change at each event */
    private ImageView view;
    // We need to set the image view for each function that we define. Therefore need multiple picture options.

    /** Button for the first action a player could make */
    private Button actionOne;

    /** Button for the second action a player could make. */
    private Button actionTwo;

    private Button artifactButton;

    /** Text containing the scenario for each specific event */
    private TextView label;

    private TextView insult;

    private Map<Integer, String> artifacts;

    private JsonObject object;

    private JSONObject thing;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        view = findViewById(R.id.background);
        // We need to set the image view for each function that we define. Therefore need multiple picture options.

        actionOne = findViewById(R.id.actionOne);

        actionTwo = findViewById(R.id.actionTwo);

        artifactButton = findViewById(R.id.artifactButton);
        artifactButton.setVisibility(View.GONE);

        label = findViewById(R.id.text);

        insult = findViewById(R.id.insult);

        artifacts = new HashMap<>();

        view.setImageResource(R.drawable.jurassic);
        originalEvent();
    }

    public void originalEvent() {
        label.setText("Stuff and Things");
        actionOne.setText("Run");
        actionTwo.setText("Kill Stuff");
        actionOne.setOnClickListener(unused -> firstEvent());
        actionTwo.setOnClickListener(unused -> secondEvent());
    }

    public void firstEvent() {
        // Begin another event or something like that
        // Image view of person running away.
        label.setText("You ran like a bitch but you got a healing charm somehow. Gonna go away soon.");
        artifacts.put(1, "Healing Charm");
        actionOne.setText("Go back and kill the beast");
        actionTwo.setText("Keep running and find shelter");
        actionOne.setOnClickListener(unused -> thirdEvent());
        actionTwo.setOnClickListener(unused -> fourthEvent());
    }

    public void secondEvent() {
        // Begin an action event or fight or something like that
        // Image of person standing with a sword.
        triviaQuestions("context");
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
        actionOne.setText("Move onto next village");
        actionTwo.setText("Continue exploring");
        actionOne.setOnClickListener(unused -> sixthEvent());
        actionTwo.setOnClickListener(unused -> eleventhEvent());
    }

    public void sixthEvent() {
        label.setText("The village is happy you are have come to protect them, but not for long. You hear a " +
                "loud bellowing from the forest");
        actionOne.setText("Investigate the forest");
        actionTwo.setText("Let the next passerby deal with it");
        actionOne.setOnClickListener(unused -> twelfthEvent());
        actionTwo.setOnClickListener(unused -> thirteenthEvent());
        artifactButton.setVisibility(View.GONE);
    }

    public void seventhEvent() {
        label.setText("You have killed the beast. You can now move onto the next village or continue exploring");
        actionOne.setText("Move onto next village");
        actionTwo.setText("Continue exploring");
        actionOne.setOnClickListener(unused -> fourteenthEvent());
        actionTwo.setOnClickListener(unused -> eleventhEvent());
    }

    public void eighthEvent() {
        if (artifacts.containsKey(1)) {
            label.setText("You killed the beast but have taken significant damage. " +
                    "Would you like to take your healing charm?");
            artifactButton.setVisibility(View.VISIBLE);
            artifactButton.setText("Use Healing Charm");
            actionOne.setText("Move onto next village");
            actionTwo.setText("Continue exploring");
            actionOne.setOnClickListener(unused -> sixthEvent());
            actionTwo.setOnClickListener(unused -> eleventhEvent());
            artifactButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    label.setText("You no longer have a healing charm!");
                    artifacts.remove(1);
                    artifactButton.setVisibility(View.GONE);
                }
            });
        } else {
            label.setText("You killed the beast but have taken significant damage. " +
                    "Would you like to move onto the next village or continue exploring?");
            actionOne.setText("Move onto the next village");
            actionTwo.setText("Continue exploring");
            actionOne.setOnClickListener(unused -> sixthEvent());
            actionTwo.setOnClickListener(unused -> eleventhEvent());
        }
    }

    public void ninthEvent() {
        label.setText("You have walked into darkness. Do you want to leave?");
        actionOne.setText("Leave");
        actionTwo.setText("Stay");
        actionOne.setOnClickListener(unused -> fifteenthEvent());
        actionTwo.setOnClickListener(unused -> sixteenthEvent());
    }

    public void tenthEvent() {
        label.setText("You left the house. Exploring the wilderness, you come across a sign." +
                "It reads 'This way to Sanctuary: a small village'. Follow the sign or no?");
        actionOne.setText("Follow the sign");
        actionTwo.setText("Don't follow the sign");
        actionOne.setOnClickListener(unused -> sixthEvent());
        actionTwo.setOnClickListener(unused -> eleventhEvent());
    }

    //When the player wants to keep exploring
    public void eleventhEvent() {
        label.setText("As you explore the dark depths of the wilderness, " +
                "fighting for your life in every corner of the world, you come across a tiny village named Lurgsberg");
        actionOne.setText("Enter Lurgsberg");
        actionTwo.setVisibility(View.GONE);
        actionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionTwo.setVisibility(View.VISIBLE);
                sixthEvent();
            }
        });
    }

    //When they investigate the forest
    public void twelfthEvent() {
        label.setText("As you slowly step into the forest, the wind picks up and nothing is certain anymore. " +
                "You hear noises all around you not knowing what is what. You see a shadow lurking behind a tree");
        actionOne.setText("Investigate shadow");
        actionTwo.setText("Get out of forest");
        actionOne.setOnClickListener(unused -> seventeenthEvent());
    }

    //When the person is in the village and doesn't want to go to the forest
    public void thirteenthEvent() {
        label.setText("");
    }

    public void fourteenthEvent() {

    }

    public void fifteenthEvent() {

    }

    public void sixteenthEvent() {

    }

    public void seventeenthEvent() {

    }

    // Nic:
    // - Work on Trivia API
    // - Alert Dialog
    // - EndGame Alert Dialog (Restart to first event (as positive button))
    // Pexels API Key: 563492ad6f91700001000001837d5ce9920e471398d93508b6d952d5
    // Zakir:
    // - Work on story
    // - Add in map variable stuff
    // - If person contains map variable, add third button.


    public void triviaQuestions(String context) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api.php?amount=1&category=18&type=multiple&encode=base64";
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            // Have to do this individually
            //byte[] decodedBytes = Base64.getDecoder().decode(stuff);
            //String decodedString = new String(decodedBytes);
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(response);
            object = element.getAsJsonObject();
        }, error -> insult.setText("Fuck"));
        queue.add(request);
        fightScene(object, context);
    }

    public void questions(String context) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api.php?amount=1&category=18&type=multiple&encode=base64";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflater = getLayoutInflater().inflate(R.layout.chunk_triviaquestions_fight,
                null, false);
        RadioGroup question = inflater.findViewById(R.id.answers);
        TextView questionBox = inflater.findViewById(R.id.question);
        TextView scenario = inflater.findViewById(R.id.scenario);
        scenario.setText(context);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {

            try {
                JSONObject object = response.getJSONArray("results").getJSONObject(0);
                questionBox.setText(object.getString("question"));
                for (int i = 0; i < object.getJSONArray("incorrect_answers").length(); i++) {
                    RadioButton otherAnswer = new RadioButton(this);
                    otherAnswer.setText(object.getJSONArray("incorrect_answers").get(i).toString());
                    question.addView(otherAnswer);
                }

                RadioButton actualAnswer = new RadioButton(this);
                String theAnswer = object.getString("correct_answers");
                actualAnswer.setText(theAnswer);
                question.addView(actualAnswer);
                builder.setPositiveButton("Enter", (dialogInterface, i) -> {
                    try {
                        String officialAnswer = response.getJSONObject("results").getString("correct_answers");
                        int index = question.getCheckedRadioButtonId();
                        RadioButton button = question.findViewById(index);
                        if (button.getText().toString().equals(officialAnswer)) {
                            insult.setText("You have survived this fight");
                        } else {
                            insult.setText("You have lost one heart");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.setView(inflater);
                dialog.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> insult.setText("Fuck"));
        queue.add(request);
    }

    public void fightScene(final JsonObject input, final String context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflater = getLayoutInflater().inflate(R.layout.chunk_triviaquestions_fight,
                null, false);
        RadioGroup question = inflater.findViewById(R.id.answers);
        JsonArray questions = input.get("results").getAsJsonArray();
        TextView questionBox = inflater.findViewById(R.id.question);
        TextView scenario = inflater.findViewById(R.id.scenario);
        scenario.setText(context);
        for (JsonElement answers : questions) {
            questionBox.setText(answers.getAsJsonObject().get("question").getAsString());
            for (JsonElement answer : answers.getAsJsonObject().get("incorrect_answers").getAsJsonArray()) {
                RadioButton otherAnswer = new RadioButton(this);
                otherAnswer.setText(answer.getAsString());
                question.addView(otherAnswer);
            }
            RadioButton actualAnswer = new RadioButton(this);
            String theAnswer = answers.getAsJsonObject().get("correct_answer").getAsString();
            actualAnswer.setText(theAnswer);
            question.addView(actualAnswer);
        }
        builder.setPositiveButton("Enter", (dialogInterface, i) -> {
            String officialAnswer = input.get("results").getAsJsonObject().get("correct_answer").getAsString();
            int index = question.getCheckedRadioButtonId();
            RadioButton button = question.findViewById(index);
            if (button.getText().toString().equals(officialAnswer)) {
                insult.setText("You have survived this fight");
            } else {
                insult.setText("You have lost one heart");
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setView(inflater);
        dialog.show();
    }

    public void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have died. Make another pathetic attempt.");
        artifacts.clear();
        builder.setPositiveButton("Try Again", (unused1, unused2) -> originalEvent());
        builder.create().show();
    }

    public void insultGenerator() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://evilinsult.com/generate_insult.php?lang=en&type=json";
        // This needs to be a Json object request
        StringRequest stringRequest = new StringRequest(url, response -> insult.setText(response.substring(0, 500)), error -> insult.setText("Fuck"));
        queue.add(stringRequest);
    }
}