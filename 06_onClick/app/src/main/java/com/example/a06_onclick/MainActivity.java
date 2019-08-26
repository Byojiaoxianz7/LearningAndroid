package com.example.a06_onclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 第二种:使用 OnClickListener 实例
        // 获取按钮
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "您点击了Button2", Toast.LENGTH_LONG).show();
            }
        });

        // 第三种:重写 onClick() 方法
        findViewById(R.id.button3).setOnClickListener(this);
    }

    // 第一种方法: 使用 onClick 属性
    public void button1(View view) {
        Toast.makeText(getApplicationContext(), "您点击了Button1", Toast.LENGTH_LONG).show();
    }

    // 第三种:重写 onClick() 方法
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "您点击了Button3", Toast.LENGTH_LONG).show();
    }
}
