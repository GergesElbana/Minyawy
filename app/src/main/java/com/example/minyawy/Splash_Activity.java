package com.example.minyawy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.VideoView;

public class Splash_Activity extends AppCompatActivity  {
    VideoView videoHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        try {
          //  VideoView video = (VideoView) findViewById(R.id.videoView);


             //videoHolder = (VideoView) findViewById(R.id.videoView);

            videoHolder = new VideoView(this);

            setContentView(videoHolder);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splashsound);
            videoHolder.setVideoURI(video);

            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, Login.class));
        finish();
    }

//        VideoView video = (VideoView) findViewById(R.id.videoView);
//        video.setVideoPath("android.resource://com.agileone/raw/" + R.raw.splashsound);
//        video.start();
//        video.setOnCompletionListener(this);
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mp)
//    {
//        Intent intent = new Intent(this, Login.class);
//        startActivity(intent);
//        finish();
//    }
}


