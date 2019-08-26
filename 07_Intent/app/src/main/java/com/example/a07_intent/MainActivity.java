package com.example.a07_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化方法
        initUI();
    }

    private void initUI() {
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("name", "韦祖杰");

        switch (view.getId()) {
            case R.id.button1:
                // 跳转到第一个界面
                intent.setClass(getApplicationContext(), FirstActivity.class);
                startActivity(intent);
                break;
        }
    }
}
