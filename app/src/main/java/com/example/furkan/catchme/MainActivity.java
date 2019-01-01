package com.example.furkan.catchme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
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
import android.widget.Toast;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    TextView ScoreText;
    TextView TimeText;
    ImageView Imageview1;
    ImageView Imageview2;
    ImageView Imageview3;
    ImageView Imageview4;
    ImageView Imageview5;
    ImageView Imageview6;
    ImageView Imageview7;
    ImageView Imageview8;
    ImageView Imageview9;
    ImageView Imageview10;
    ImageView Imageview11;
    ImageView Imageview12;
    ImageView Imageview13;
    ImageView Imageview14;
    ImageView Imageview15;
    Button btn_Sound,btn_Pause;
    int score;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    MediaPlayer gameMusic,gameOversound,alienVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        Imageview1 = (ImageView)findViewById(R.id.imageView1);
        Imageview2 = (ImageView)findViewById(R.id.imageView2);
        Imageview3 = (ImageView)findViewById(R.id.imageView3);
        Imageview4 = (ImageView)findViewById(R.id.imageView4);
        Imageview5 = (ImageView)findViewById(R.id.imageView5);
        Imageview6 = (ImageView)findViewById(R.id.imageView6);
        Imageview7 = (ImageView)findViewById(R.id.imageView7);
        Imageview8 = (ImageView)findViewById(R.id.imageView8);
        Imageview9 = (ImageView)findViewById(R.id.imageView9);
        Imageview10 = (ImageView)findViewById(R.id.imageView10);
        Imageview11 = (ImageView)findViewById(R.id.imageView11);
        Imageview12 = (ImageView)findViewById(R.id.imageView12);
        Imageview13 = (ImageView)findViewById(R.id.imageView13);
        Imageview14 = (ImageView)findViewById(R.id.imageView14);
        Imageview15 = (ImageView)findViewById(R.id.imageView15);

        btn_Pause = findViewById(R.id.button_Pause);
        btn_Sound = findViewById(R.id.button_Sound);

        imageArray = new ImageView[]{Imageview1,Imageview2,Imageview3,Imageview4,Imageview5,Imageview6,Imageview7,Imageview8,Imageview9,Imageview10,
        Imageview11,Imageview12,Imageview13,Imageview14,Imageview15};
        hideImagines();
        alienVoice=MediaPlayer.create(MainActivity.this,R.raw.alienvoice);
        gameMusic = MediaPlayer.create(MainActivity.this,R.raw.gamemusic);
        gameMusic.start();
        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                TimeText = (TextView) findViewById(R.id.tvTime);
                TimeText.setText("" + l / 1000);
            }

            @Override
            public void onFinish() {
                TimeText = (TextView) findViewById(R.id.tvTime);
                TimeText.setText("Süre Doldu");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                gameMusic.stop();
                gameOversound = MediaPlayer.create(MainActivity.this, R.raw.gameoversound);
                gameOversound.start();

                Intent intent = new Intent(getApplicationContext(), Resultview.class);
                intent.putExtra("Score", score);
                startActivity(intent);

            }
        }.start();

    }

    public void increaseScore (View view){
        ScoreText = findViewById(R.id.tvScore);
        score++;
        ScoreText.setText(""+score);
        alienVoice.start();

    }
    public void btnPause(View view){

            handler.removeCallbacks(runnable);
            for (ImageView image : imageArray) {
                image.setVisibility(View.INVISIBLE);
            }
            btn_Pause.setBackgroundResource(R.drawable.btn_play);



    }
    public void btnSoundOff(View view){
        if(gameMusic.isPlaying())
        {
            gameMusic.pause();
            btn_Sound.setBackgroundResource(R.drawable.btn_soundoff);
        }
        else {
            gameMusic.start();
            btn_Sound.setBackgroundResource(R.drawable.btn_soundon);
        }

    }

    @Override
    public void onBackPressed() {
       Toast toast = Toast.makeText(getApplicationContext(),"Hemen pes etme :)",Toast.LENGTH_SHORT);
       toast.show();
    }

    public void hideImagines(){
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random rnd = new Random();
                int random = rnd.nextInt(11-0);

                //Random rand = new Random();
                //int randomDelay = rand.nextInt(1500-1000);
                imageArray[random].setVisibility(View.VISIBLE);
                handler.postDelayed(this,550);
            }
        };

        handler.post(runnable);



    }
}
