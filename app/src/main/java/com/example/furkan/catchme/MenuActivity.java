package com.example.furkan.catchme;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    MediaPlayer menuSound;
    int i=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuSound = MediaPlayer.create(MenuActivity.this,R.raw.gamemusic);
        menuSound.start();


    }
    public void btn_Play (View view){
                menuSound.stop();
                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
    }

    public void OyundanCik (View view){
                System.exit(0);
    }

}
