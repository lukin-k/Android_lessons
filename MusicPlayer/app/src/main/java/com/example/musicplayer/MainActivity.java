package com.example.musicplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageView imageViewPlayPause;
    SeekBar seekBarVolumeLevel;
    AudioManager audioManager;
    SeekBar seekBarTrackProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer=MediaPlayer.create(this, R.raw.stuff);

        audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);

        imageViewPlayPause=findViewById(R.id.imageViewPlayPause);

        seekBarVolumeLevel=findViewById(R.id.seekBarVolumeLevel);
        seekBarVolumeLevel.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        seekBarVolumeLevel.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        seekBarVolumeLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarTrackProgress=findViewById(R.id.seekBarTrackProgress);
        seekBarTrackProgress.setMax(mediaPlayer.getDuration());
        seekBarTrackProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                    mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBarTrackProgress.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
//        mediaPlayer.start();
    }

    public void PlayPause(View view) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            imageViewPlayPause.setImageResource(R.drawable.ic_play_arrow_green_64dp);
        }else{
            mediaPlayer.start();
            imageViewPlayPause.setImageResource(R.drawable.ic_pause_red_64dp);
        }
    }

    public void toStart(View view) {
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
        imageViewPlayPause.setImageResource(R.drawable.ic_pause_red_64dp);
    }

    public void toEnd(View view) {
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.pause();
        imageViewPlayPause.setImageResource(R.drawable.ic_play_arrow_green_64dp);
    }
}
