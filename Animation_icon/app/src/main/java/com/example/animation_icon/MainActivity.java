package com.example.animation_icon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    ImageView imageCheck;
    ImageView imageClose;
    long speedAnimation=3000;
    SeekBar seekBarSpeedAnimation;

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageCheck=findViewById(R.id.imageCheck);
        imageClose=findViewById(R.id.imageClose);
        imageClose.setAlpha(0f);

        seekBarSpeedAnimation=findViewById(R.id.seekBarSpeedAnimation);
        seekBarSpeedAnimation.setMax((int)speedAnimation);
        seekBarSpeedAnimation.setProgress(seekBarSpeedAnimation.getMax());
//        seekBarSpeedAnimation.setMin(0);

        videoView=findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.demo);

        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
//        videoView.start();
    }

    public void DoSomething(View view) {
        speedAnimation=seekBarSpeedAnimation.getProgress();
        if(imageCheck.getAlpha()==0f){
            imageClose.animate().alpha(0).rotation(-360).scaleX(0).scaleY(0).setDuration(speedAnimation);
            imageCheck.animate().alpha(1).rotation(360).scaleX(1).scaleY(1).setDuration(speedAnimation);
        }else{
            imageCheck.animate().alpha(0).rotation(-360).scaleX(0).scaleY(0).setDuration(speedAnimation);
            imageClose.animate().alpha(1).rotation(360).scaleX(1).scaleY(1).setDuration(speedAnimation);
        }
    }
}
