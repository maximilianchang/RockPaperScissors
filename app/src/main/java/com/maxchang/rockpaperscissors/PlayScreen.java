package com.maxchang.rockpaperscissors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayScreen extends AppCompatActivity {

    public static final int PAPER = 0;
    public static final int ROCK = 1;
    public static final int SCISSORS = 2;
    public static final int NUM_POSSIBILITIES = 3;

    public static final int DRAW = -1;
    public static final int USER_WIN = 1;
    public static final int USER_LOSS = 0;

    private int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
    }

    public void optionSelected(View view) {
        int userSelection = 0;
        int computerSelection = chooseOption();

        switch(view.getId()) {
            case R.id.paperButton:
                userSelection = PAPER; break;
            case R.id.rockButton:
                userSelection = ROCK; break;
            case R.id.scissorsButton:
                userSelection = SCISSORS; break;
            default: Log.i("PlayScreen", "Invalid Option Selected");
        }

        int result = calculateResult(computerSelection, userSelection);
        displayResult(result);
    }

    //Allows the computer to randomly choose an option.
    private int chooseOption() {
        Random r = new Random();
        int selection = r.nextInt(NUM_POSSIBILITIES);
        ImageView display = (ImageView) findViewById(R.id.computerSelectionImage);
        switch (selection) {
            case ROCK:
                display.setImageResource(R.drawable.rock); break;
            case PAPER:
                display.setImageResource(R.drawable.paper); break;
            case SCISSORS:
                display.setImageResource(R.drawable.scissors); break;
            default: Log.i("PlayScreen", "Computer Selected Invalid Option");
        }

        return selection;
    }

    //Calculates if the user won, loss, or drew with the computer.
    private int calculateResult(int computerChoice, int userChoice) {
        if (computerChoice == userChoice) return DRAW;

        if ((userChoice == ROCK && computerChoice == SCISSORS) ||
                (userChoice == PAPER && computerChoice == ROCK) ||
                (userChoice == SCISSORS && computerChoice == PAPER)) {
            points++;
            return USER_WIN;
        } else {
            points--;
            return USER_LOSS;
        }
    }

    private void displayResult(int result) {
        switch (result) {
            case USER_WIN:
                Toast.makeText(this, "Congratulations! You Won!", Toast.LENGTH_SHORT).show();
                break;
            case USER_LOSS:
                Toast.makeText(this, "Sorry, you lost. :(", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "It was a draw.", Toast.LENGTH_SHORT).show();
        }

        TextView highScore = (TextView) findViewById(R.id.scoreBoard);
        highScore.setText("Score: " + String.valueOf(points));
    }
}
