package com.zyxcyj1217.attackoftheslime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }


    public void button_play(View view) {
        et = (EditText)findViewById(R.id.editText_min);
        valMin = Integer.parseInt(et.getText().toString());
        et = (EditText)findViewById(R.id.editText_max);
        valMax = Integer.parseInt(et.getText().toString());

        if(valMax - valMin < 4) {
            Toast.makeText(this, "那诶怪怪", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent();
            intent.setClass(PlayActivity.this , GameActivity.class);
            startActivity(intent);
        }
    }


    public static int valMin;
    public static int valMax;
    EditText et;
    
    public void button_word(View view) {
        et = (EditText)findViewById(R.id.editText_min);
        valMin = Integer.parseInt(et.getText().toString());
        et = (EditText)findViewById(R.id.editText_max);
        valMax = Integer.parseInt(et.getText().toString());

        if(valMax - valMin < 4) {
            Toast.makeText(this, "那诶怪怪", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent();
            intent.setClass(PlayActivity.this , WordActivity.class);
            startActivity(intent);
        }
    }

    
    public void button_back(View view) {
        finish();
    }

}
