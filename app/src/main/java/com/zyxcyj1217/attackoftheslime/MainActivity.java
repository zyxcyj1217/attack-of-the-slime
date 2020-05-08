package com.zyxcyj1217.attackoftheslime;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(fileIsExists("data.txt") == false) {
            String filename = "data.txt";
            try {
                FileOutputStream fos = openFileOutput(filename, Context.MODE_APPEND);
                OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
                osw.write("0,");

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
        }

    }

    public boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
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
