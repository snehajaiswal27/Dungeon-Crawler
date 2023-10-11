package com.example.dungeoncrawler.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dungeoncrawler.R;
import com.example.dungeoncrawler.models.Leaderboard;
import com.example.dungeoncrawler.models.Player;
import com.example.dungeoncrawler.models.Score;
import com.example.dungeoncrawler.viewmodels.OverarchingViewmodel;

public class GameScene extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_scene);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String username = OverarchingViewmodel.getPlayerName();
        int difficulty = extras.getInt("difficulty");
        int health = OverarchingViewmodel.getPlayerHealth();
        String difficultyLevel = extras.getString("DifficultyLevel");

        TextView nameTextView = findViewById(R.id.nameText);
        nameTextView.setText("Hi " + username);

        ImageView spriteView = findViewById(R.id.spriteView);
        spriteView.setImageResource(OverarchingViewmodel.getPlayerSprite());
//        if(sprite == 1) {
//            spriteView.setImageResource(R.drawable.male_elf);
//        } else if(sprite == 2) {
//            spriteView.setImageResource(R.drawable.female_wizard);
//        } else if(sprite == 3) {
//            spriteView.setImageResource(R.drawable.female_dwarf);
//        }

        TextView difficultyTextView = findViewById(R.id.difficultyText);
        difficultyTextView.setText("Difficulty level " + difficulty + ": " + difficultyLevel);

        TextView healthTextView = findViewById(R.id.healthText);
        healthTextView.setText("You have " + health + " health");

        Button endingButton = findViewById(R.id.endingButton);

        endingButton.setOnClickListener(v -> {
            OverarchingViewmodel.addScore(username);
            Intent ending = new Intent(GameScene.this, Ending.class);
            startActivity(ending);
            finish();
        });

        TextView scoreText = findViewById(R.id.scoreText);
        new CountDownTimer(100000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                scoreText.setText("Score: " + OverarchingViewmodel.decreaseScore(1));
            }

            @Override
            public void onFinish() {}

        }.start();
    }
}