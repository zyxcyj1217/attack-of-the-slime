package com.zyxcyj1217.attackoftheslime;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Resources resources = getResources();
        Drawable btnDrawable = resources.getDrawable(R.drawable.button_round);
        LinearLayout linear = (LinearLayout) findViewById(R.id.linearLayout);

        int valMin = PlayActivity.valMin;
        int valMax = PlayActivity.valMax;
        int i;

        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("test.csv"),"UTF-8");
            BufferedReader reader = new BufferedReader(isr);
            String line;

            for(i = 1; i < valMin; i++) {
                reader.readLine();
            }

            for(i = valMin; i <= valMax; i++) {

                line = reader.readLine();
                String buffer[] = line.split(",");

                LinearLayout.LayoutParams para = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
                para.bottomMargin = 25;
                para.height = 200;

                Button btn = new Button(WordActivity.this);

                btn.setBackground(btnDrawable);
                btn.setText(buffer[0] + "\n" + buffer[1]);
                btn.setLayoutParams(para);
                btn.setAllCaps(false);

                linear.addView(btn);
            }
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 返回鍵
    public void button_back(View view) {
        finish();
    }

}
