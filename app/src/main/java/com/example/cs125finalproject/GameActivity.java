package com.example.cs125finalproject;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;
import java.util.HashMap;
import java.util.Base64;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

        originalEvent();
    }

    public void originalEvent() {
        label.setText("Welcome to the magical game of Gondordo! A game where the odds are forever in your favor. " +
                "Have fun roaming the depths of the wilderness but beware... there are monsters everywhere");
        actionOne.setText("Begin your journey");
        actionTwo.setVisibility(View.GONE);
        actionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionTwo.setVisibility(View.VISIBLE);
                midEvent();
            }
        });
    }

    //start of the story.
    public void midEvent() {
        label.setText("The story starts off with you roaming the wild and coming across a very dangerous monster. " +
                "This monster, different from others, it has fangs");
        actionOne.setText("Run from monster");
        actionTwo.setText("Try and kill monster");
        view.setImageResource(R.drawable.jurassic);
        actionOne.setOnClickListener(unused -> firstEvent());
        actionTwo.setOnClickListener(unused -> secondEvent());
    }

    public void firstEvent() {
        // Begin another event or something like that
        // Image view of person running away.
        label.setText("You ran away! Is this really how you want to start your journey.");
        actionOne.setText("Go back and kill the beast");
        actionTwo.setText("Keep running and find shelter");
        actionOne.setOnClickListener(unused -> thirdEvent());
        actionTwo.setOnClickListener(unused -> fourthEvent());
    }

    public void secondEvent() {
        // Begin an action event or fight or something like that
        // Image of person standing with a sword.
        triviaQuestions("You stayed and fought the beast and killed it!");
        label.setText("You stayed and fought the beast and killed it!");
        actionOne.setText("Eat the guts of the beast");
        actionTwo.setText("Move on to the next village");
        actionOne.setOnClickListener(unused -> fifthEvent());
        actionTwo.setOnClickListener(unused -> sixthEvent());
    }

    public void thirdEvent() {
        label.setText("You went back for the beast. Laying on the ground you find a healing charm. " +
                "Kill him with either a sword or a knife");
        artifacts.put(1, "Healing Charm");
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
        artifacts.put(1, "Healing Charm");
        actionOne.setText("Move onto next village");
        actionTwo.setText("Continue exploring");
        actionOne.setOnClickListener(unused -> sixthEvent());
        actionTwo.setOnClickListener(unused -> eleventhEvent());
    }

    public void sixthEvent() {
        view.setImageResource(R.drawable.village);
        label.setText("The village is happy you are have come to protect them, but not for long. You hear a " +
                "loud bellowing from the forest");
        actionOne.setText("Investigate the forest");
        actionTwo.setText("Let the next passerby deal with it");
        actionOne.setOnClickListener(unused -> twelfthEvent());
        actionTwo.setOnClickListener(unused -> thirteenthEvent());
        artifactButton.setVisibility(View.GONE);
    }

    public void seventhEvent() {
        label.setText("You have killed the beast. By killing him, you have gained the healing charm. You can now move onto the " +
                "next village or continue exploring");
        artifacts.put(1, "Healing Charm");
        actionOne.setText("Move onto next village");
        actionTwo.setText("Continue exploring");
        actionOne.setOnClickListener(unused -> sixthEvent());
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
        actionOne.setOnClickListener(unused -> fourteenthEvent());
        actionTwo.setOnClickListener(unused -> fifteenthEvent());
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
        view.setImageResource(R.drawable.darkforest);
        label.setText("As you slowly step into the forest, the wind picks up and nothing is certain anymore. " +
                "You hear noises all around you not knowing what is what. You see a shadow lurking behind a tree");
        actionOne.setText("Investigate shadow");
        actionTwo.setText("Get out of forest");
        actionOne.setOnClickListener(unused -> sixteenthEvent());
        actionTwo.setOnClickListener(unused -> seventeenthEvent());
    }

    //When the person is in the village and doesn't want to go to the forest
    public void thirteenthEvent() {
        label.setText("The girls in the village call you a pussy. Holding back tears to not embarrass yourself, " +
                "you reluctantly enter the forest.");
        actionOne.setText("Enter the forest");
        actionTwo.setVisibility(View.GONE);
        actionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionTwo.setVisibility(View.VISIBLE);
                twelfthEvent();
            }
        });
    }

    //Leave the darkness
    public void fourteenthEvent() {
        label.setText("Tis a good option. As you leave the darkness, you decide to explore the wilderness. " +
                "Fighting for you life, you come across a small village named Lurgsberg");
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

    //Stays in the darkness from event 9
    public void fifteenthEvent() {
        badEndGameOne();
    }

    //When the player approaches the shadow lurking behind the tree
    public void sixteenthEvent() {
        label.setText("As you approach the shadow, a leprechaun pops out from behind the tree. He states " +
                "'My name is Challen. I love my name!' and proceeds to throw a magical coin at you and runs away.");
        actionOne.setText("Pick up the coin");
        actionTwo.setText("Follow the leprechaun before he gets away");
        actionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                artifacts.put(2, "Magical Coin");
                eighteenthEvent();
            }
        });
        actionTwo.setOnClickListener(unused -> nineteenthEvent());
    }

    //When the player gets out of the forest instead of investigating the shadow
    public void seventeenthEvent() {
        label.setText("Leaving the forest was a bad option. The village has kicked you out of the area. " +
                "You must live out the rest of your days scavenging for food. You must find a way to survive");
        actionOne.setText("Enter forest again");
        actionTwo.setText("Accept death");
        actionOne.setOnClickListener(unused -> twelfthEvent());
        actionTwo.setOnClickListener(unused -> badEndGameFour());
    }

    //After the player picks up the magical coin
    public void eighteenthEvent() {
        label.setText("Congrats! You have added the magical coin to your artifact pouch. You now have the option to try figuring out if you " +
                "would like to track the leprechaun or head back to the village");
        actionOne.setText("Track leprechaun");
        actionTwo.setText("Head back to the village");
        actionOne.setOnClickListener(unused -> nineteenthEvent());
        actionTwo.setOnClickListener(unused -> twentyEvent());
    }

    //Following the leprechaun
    public void nineteenthEvent() {
        label.setText("Using your 'detective-like' skills, you follow the footsteps created in the mud by the leprechaun. " +
                "Suddenly, they stop and you are completely lost. You hear giggling but you have no idea where it's coming from.");
        actionOne.setText("Go east toward the giggling");
        actionTwo.setText("Go west toward the giggling");
        actionOne.setOnClickListener(unused -> twentyOneEvent());
        actionTwo.setOnClickListener(unused -> twentyTwoEvent());
    }

    //If the person gets the coin and goes back to village
    public void twentyEvent() {
        label.setText("As you get back to the village, many start to ask questions. You answer with dead silence. " +
                "They start to pressure you into giving up the coin in order to leave you alone");
        actionOne.setText("Give up the coin");
        actionTwo.setText("Punch a couple and run");
        actionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                artifacts.remove(2);
                twentyThreeEvent();
            }
        });
        actionTwo.setOnClickListener(unused -> twentyFourEvent());
    }

    //If the person goes east toward the actual leprechaun
    public void twentyOneEvent() {
        label.setText("Sitting to the east, you find the leprechaun sitting with his pot of gold. He gives you an option, " +
                "either answer a riddle or fight him for the gold.");
        actionOne.setText("Answer the riddle");
        actionTwo.setText("Fight the leprechaun");
        actionOne.setOnClickListener(unused -> twentyFiveEvent());
        actionTwo.setOnClickListener(unused -> badEndGameTwo());
    }

    //If the person goes west toward the cave of lurkers.
    public void twentyTwoEvent() {
        label.setText("You walk towards a cave. Dark caves have the chance of having lurkers.");
        actionOne.setText("Enter cave");
        actionTwo.setText("Go back");
        actionOne.setOnClickListener(unused -> twentySixEvent());
        actionTwo.setOnClickListener(unused -> nineteenthEvent());

    }

    //If the person gives up the coin to the village
    public void twentyThreeEvent() {
        label.setText("You no longer have the coin. You flee");
    }

    //If the person punches there way out of the village with the coin
    public void twentyFourEvent() {
        label.setText("You leave with the coin. Running into the forest, an angry mob is behind you.");
    }

    //If the player decides to answer the riddle of the leprechaun
    public void twentyFiveEvent() {
        label.setText("The leprechaun gives you to following riddle: 'I have cities, but no houses. I have mountains," +
                " but no trees. I have water, but no fish. What am I?'");
        actionOne.setText("Colorado");
        actionTwo.setText("Chernobyl");
        artifactButton.setVisibility(View.VISIBLE);
        artifactButton.setText("A Map");
        actionOne.setOnClickListener(unused -> badEndGameThree());
        actionTwo.setOnClickListener(unused -> badEndGameThree());
        artifactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setImageResource(R.drawable.jurassic);
                artifactButton.setVisibility(View.GONE);
                goodEndGame();
            }
        });
    }

    //If the user enters the cave of DOOM
    public void twentySixEvent() {
        label.setText("You have entered the cave of DOOM. The lurkers hear you and want to feed on your flesh");
        actionOne.setText("Move further into cave");
        actionTwo.setText("Exit cave");
        actionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                badEndGameFive();
            }
        });
        actionTwo.setOnClickListener(unused -> twentyTwoEvent());
    }

    // 2160 X 1215

    public void triviaQuestions(final String context) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://opentdb.com/api.php?amount=1&category=18&type=multiple&encode=base64";
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(response);
            fightScene(element.getAsJsonObject(), context);
        }, error -> insult.setText("Fuck"));
        queue.add(request);
    }

    public void fightScene(final JsonObject input, final String context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflater = getLayoutInflater().inflate(R.layout.chunk_triviaquestions_fight,
                null, false);
        RadioGroup question = inflater.findViewById(R.id.answers);
        object = input;
        JsonArray questions = input.get("results").getAsJsonArray();
        TextView questionBox = inflater.findViewById(R.id.question);
        TextView scenario = inflater.findViewById(R.id.scenario);
        scenario.setText(context);
        for (JsonElement answers : questions) {
            String theQuestion = answers.getAsJsonObject().get("question").getAsString();
            byte[] decodedBytes = Base64.getDecoder().decode(theQuestion);
            String decodedString1 = new String(decodedBytes);
            questionBox.setText(decodedString1);
            for (JsonElement answer : answers.getAsJsonObject().get("incorrect_answers").getAsJsonArray()) {
                RadioButton otherAnswer = new RadioButton(this);
                String incorrectAnswer = answer.getAsString();
                byte[] decodedBytes2 = Base64.getDecoder().decode(incorrectAnswer);
                String decodedString2 = new String(decodedBytes2);
                otherAnswer.setText(decodedString2);
                question.addView(otherAnswer);
            }
            RadioButton actualAnswer = new RadioButton(this);
            String theAnswer = answers.getAsJsonObject().get("correct_answer").getAsString();
            byte[] decodedBytes3 = Base64.getDecoder().decode(theAnswer);
            String decodedString3 = new String(decodedBytes3);
            actualAnswer.setText(decodedString3);
            question.addView(actualAnswer);
        }
        builder.setPositiveButton("Enter", (dialogInterface, i) -> {
            for (JsonElement answers : questions) {
                String theAnswer = answers.getAsJsonObject().get("correct_answer").getAsString();
                byte[] decodedBytes3 = Base64.getDecoder().decode(theAnswer);
                String decodedString3 = new String(decodedBytes3);
                int index = question.getCheckedRadioButtonId();
                RadioButton button = question.findViewById(index);
                if (button.getText().toString().equals(decodedString3)) {
                    insult.setText("You have survived this fight");
                } else {
                    badEndGameOne();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setView(inflater);
        dialog.show();
    }

    public void badEndGameOne() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have died. Make another pathetic attempt.");
        artifacts.clear();
        builder.setPositiveButton("Try Again", (unused1, unused2) -> originalEvent());
        builder.create().show();
    }

    public void badEndGameTwo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The leprechaun had a dragon lurking in the shadows. It killed you");
        artifacts.clear();
        builder.setPositiveButton("Try Again", (unused1, unused2) -> originalEvent());
        builder.create().show();
    }

    public void badEndGameThree() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You got the riddle wrong. The leprechaun dropped a tree on you for being stupid");
        artifacts.clear();
        builder.setPositiveButton("Try Again", (unused1, unused2) -> originalEvent());
        builder.create().show();
    }

    public void badEndGameFour() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You died because you were a pussy");
        artifacts.clear();
        builder.setPositiveButton("Start Over", (unused1, unused2) -> originalEvent());
        builder.create().show();
    }

    public void badEndGameFive() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The lurkers got you. They ate your flesh and you died.");
        artifacts.clear();
        builder.setPositiveButton("Try Again", (unused1, unused2) -> originalEvent());
        builder.create().show();
    }

    public void goodEndGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Congratulations! You got the pot of gold and live happily ever after in a big mansion. " +
                "You have successfully finished the game!");
        artifacts.clear();
        builder.setPositiveButton("Play Again", (unused1, unused2) -> originalEvent());
        builder.create().show();
    }
}
