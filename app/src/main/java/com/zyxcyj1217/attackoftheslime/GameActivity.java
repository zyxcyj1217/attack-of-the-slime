package com.zyxcyj1217.attackoftheslime;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    int valMin = PlayActivity.valMin;
    int valMax = PlayActivity.valMax;

    int ansBnt;

    List answer = new ArrayList();
    List option = new ArrayList();
    List random = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        readFile();

        setAns();


/*
        btn1.setText((String)option.get(getRan()));
        btn2.setText((String)option.get(getRan()));
        btn3.setText((String)option.get(getRan()));
        btn4.setText((String)option.get(getRan()));
*/

    }

    public void setAns() {
        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        Button btn4 = (Button)findViewById(R.id.button4);
        TextView tv = (TextView)findViewById(R.id.textView0);

        int a;
        ansBnt = getRan(0, 3);
        do {
             a = getRan(0, 3);
        }while (a == ansBnt);
        ansBnt = a;

        setRan(0, valMax - valMin , 4);
        btn1.setText((String)option.get((Integer) random.get(0)));
        btn2.setText((String)option.get((Integer) random.get(1)));
        btn3.setText((String)option.get((Integer) random.get(2)));
        btn4.setText((String)option.get((Integer) random.get(3)));
        tv.setText((String)answer.get((Integer) random.get(ansBnt)));
    }

    public void setRan(int min, int max, int iMax) {
        random.clear();
        int i;
        int r;
        for(i = 0; i < iMax; i++) {
            do {
                r = getRan(min, max);
            }while(random.contains(r));
            random.add(r);
        }
    }

    public int getRan(int min, int max) {
        return (int)(Math.random() * (max - min + 1) + min);
    }

    public void readFile() {
        int i;
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("test.csv"),"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            for(i = 1; i < valMin; i++) {
                br.readLine();
            }
            for(i = valMin; i <= valMax; i++) {
                line = br.readLine();
                String buffer[] = line.split(",");
                answer.add(buffer[0]);
                option.add(buffer[1]);
            }
            isr.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void ansY() {
        setAns();
    }

    public void ansN() {

    }

    public void button1(View view) {
        if(ansBnt == 0) {
            ansY();
        }else{
            ansN();
        }
    }

    public void button2(View view) {
        if(ansBnt == 1) {
            ansY();
        }else{
            ansN();
        }
    }

    public void button3(View view) {
        if(ansBnt == 2) {
            ansY();
        }else{
            ansN();
        }
    }

    public void button4(View view) {
        if(ansBnt == 3) {
            ansY();
        }else{
            ansN();
        }
    }
}
