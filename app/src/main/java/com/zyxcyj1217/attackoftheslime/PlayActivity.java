package com.zyxcyj1217.attackoftheslime;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        TextView tv = (TextView)findViewById(R.id.textView_top);
        String res="";
        String filename = "data.txt";
        try {
            FileInputStream fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            char[] input = new char[fis.available()];
            isr.read(input);

            isr.close();
            fis.close();

            String str[] = new String(input).split(",");
            res = str[0];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv.setText("最高紀錄第 " + res + " 層");
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
            finish();
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
