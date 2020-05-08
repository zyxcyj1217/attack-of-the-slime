package com.zyxcyj1217.attackoftheslime;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    int valMin = PlayActivity.valMin;
    int valMax = PlayActivity.valMax;

    int ansBnt;

    int wave = 1;
    int playHp = 300;
    int playDmg = 60;
    int bossHp = 300;
    int bossDmg = 0;
    int timeBar = 0;

    List answer = new ArrayList();
    List option = new ArrayList();
    List random = new ArrayList();

    String date;

    private Handler handler = new Handler( );
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        readFile();

        setAns();

        runnable = new Runnable( ) {
            public void run ( ) {
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //獲取繫系時間
                date = sDateFormat.format(new java.util.Date());
                Log.d("Date","獲取時間"+date);

                Button time1 = (Button)findViewById(R.id.timebar);

                timeBar += 2;
                if(timeBar >= 300) {
                    timeBar = 0;
                    ansN();
                }

                int tw = dp2px(getApplicationContext(), timeBar);
                int w = dp2px(getApplicationContext(), 20);

                setMargins(time1, tw, 0, w, w);

                handler.postDelayed(this,1);
                //postDelayed(this,2000)方法安排一個Runnable物件到主執行緒佇列中
            }
        };

        handler.postDelayed(runnable,5); // 開始Timer
    }

    public void setAns() {
        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        Button btn4 = (Button)findViewById(R.id.button4);
        TextView tv = (TextView)findViewById(R.id.textView_question);

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

    public void attacking() {
        Button   hp = (Button)   findViewById(R.id.hp_boss);
        TextView wv = (TextView) findViewById(R.id.textView_wave);

        if(bossHp - playDmg > 0){
            bossHp -= playDmg;
        }else{
            bossHp = 300;
            wave++;
            wv.setText("第 " + wave + " 層");
        }

        int w = dp2px(this, bossHp);
        hp.getLayoutParams().width = w;

        timeBar = 0;

        setAns();
    }

    public void ansN() {
        Button hp = (Button)findViewById(R.id.hp_play);
        bossDmg = (int) (Math.sqrt(wave) * 20);

        if(playHp - bossDmg > 0) {
            playHp -= bossDmg;
        }
        else {
            playHp = 1;

            handler.removeCallbacks(runnable);

            String res = "";

            String filename = "data.txt";
            try {
                FileInputStream fis = openFileInput(filename);
                InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
                char[] input = new char[fis.available()];
                isr.read(input);

                isr.close();
                fis.close();

                String[] str = new String(input).split(",");
                res = str[0];

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(Integer.parseInt(res) > wave) {
                Toast toast = Toast.makeText(this, "你也太廢了吧", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

            }else{
                try {

                    FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
                    osw.write(Integer.toString(wave) + ",");

                    osw.flush();
                    fos.flush();  //flush是为了输出缓冲区中所有的内容

                    osw.close();
                    fos.close();  //写入完成后，将两个输出关闭

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast toast = Toast.makeText(this, "你達到第 " + wave + " 層了！", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
            handler.removeCallbacks(runnable);
            Intent intent = new Intent();
            intent.setClass(GameActivity.this , PlayActivity.class);
            startActivity(intent);
            finish();
        }

        int w = dp2px(this, playHp);

        hp.getLayoutParams().width = w;

        timeBar = 0;

        setAns();
    }


    // set margins
    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }


    // dp to px
    public static int dp2px(Context context,float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }


    // 按鈕事件
    public void button1(View view) { if(ansBnt == 0) attacking(); else ansN(); }
    public void button2(View view) { if(ansBnt == 1) attacking(); else ansN(); }
    public void button3(View view) { if(ansBnt == 2) attacking(); else ansN(); }
    public void button4(View view) { if(ansBnt == 3) attacking(); else ansN(); }

}
