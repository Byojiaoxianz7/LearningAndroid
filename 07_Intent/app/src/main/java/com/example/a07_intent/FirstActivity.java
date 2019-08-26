package com.example.a07_intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        Log.i("FirstActivity", "获取的名字是:" + name);

        TextView tvShow = findViewById(R.id.tv_show);
        tvShow.setText("获取的姓名为:" +name);
    }
}
