package com.example.furkan.catchme;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Resultview extends AppCompatActivity {

    TextView lastScore,highScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultview);

        lastScore = findViewById(R.id.tv_LastScore);
        highScore = findViewById(R.id.tv_HighScore);

        int score = getIntent().getIntExtra("Score",0);
        lastScore.setText(score+"");

        SharedPreferences settings = getSharedPreferences("GameData", Context.MODE_PRIVATE);
        int highscore = settings.getInt("High_Score",0);

        if(score>highscore){
            highScore.setText("Yüksek Skor : "+score);

            //Kaydetme
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("High_Score",score);
            editor.commit();
        }
        else {
            highScore.setText("Yüksek Skor : "+highscore);
        }
    }
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(i);
    }
    public void btnMenu(View view){
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(i);
    }

    public void playAgain(View view){
        Intent intent = new Intent(Resultview.this,MainActivity.class);
        startActivity(intent);
    }
}
