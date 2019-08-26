package com.example.a08_api;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvShow;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private int GET_DATA_SUCCESS = 101;  // get data success


    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if (message.what==GET_DATA_SUCCESS) {
                String data = message.getData().getString("data");
                Log.i("MainActivity", data);
                mTvShow.setText(data);
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        initData();
    }

    private void initUI() {
        // 获取文本框
        mTvShow = findViewById(R.id.tv_show);
        // 获取按钮并且绑定监听者对象
        findViewById(R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        initData();
    }

    // 初始化数据
    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getDataFromServer();
                Log.i("MainActivity", "获取数据为:"+data);

                // create message object
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                message.setData(bundle);
                message.what = GET_DATA_SUCCESS;

                // 向主线程发信息
                mHandler.sendMessage(message);
            }
        }).start();
    }

    // 从服务器获取数据
    private String getDataFromServer() {
        try {
            // 1. create url
            URL url = new URL("https://v1.hitokoto.cn/?c=f&encode=text");

            // 2. open link
            connection = (HttpURLConnection) url.openConnection();

            // 3. judge and process the results
            if (connection.getResponseCode() == 200) {
                // get input stream
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                for (String line = "";(line = bufferedReader.readLine())!=null;) {
                    stringBuilder.append(line);
                }

                return stringBuilder.toString();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader!=null)bufferedReader.close();
                if (inputStream!=null)inputStream.close();
                if (connection!=null)connection.disconnect();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "None";

    }
}
