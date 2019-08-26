package com.example.happybirthday;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 现实布局
        setContentView(R.layout.activity_main);
        // 初始化布局
        initUI();
    }

    private void initUI() {
        // 获取媒体播放对象
        final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);

        // 注册点击事件
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }

            }
        });
    }
}
