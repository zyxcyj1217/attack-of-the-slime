package com.zyxcyj1217.attackoftheslime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_play(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this , PlayActivity.class);
        startActivity(intent);
    }

    // 離開程式
    public void button_exit(View view) {
        //android.os.Process.killProcess(android.os.Process.myPid());
        finish();
    }

}
