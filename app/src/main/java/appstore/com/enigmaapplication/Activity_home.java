package appstore.com.enigmaapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Activity_home extends AppCompatActivity {
    Button b1;
    TextView tv1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        b1 = (Button) findViewById(R.id.b1);
        tv1 = (TextView) findViewById(R.id.tv1);
        //String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        final Handler handler = new Handler(getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv1.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                handler.postDelayed(this,1000);/*
                String s[] = tv1.getText().toString().split(" ");
                String s1[] = s[0].split("-");
                String s2[] = s[1].split(":");
                int day,hr,min,sec;
                if(Integer.parseInt(s[2])<=2)
                    day = 2 - Integer.parseInt(s1[2]);
                else
                    day = 30 - Integer.parseInt(s1[2])+2;*/
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date d1 = simpleDateFormat.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    Date d2 = simpleDateFormat.parse("2018-10-02 10:00:00");
                    long num_seconds = (d2.getTime() - d1.getTime())/1000;
                    long days = num_seconds / (60 * 60 * 24);
                    num_seconds -= days * (60 * 60 * 24);
                    long hours = num_seconds / (60 * 60);
                    num_seconds -= hours * (60 * 60);
                    long minutes = num_seconds / 60;

                    tv1.setText(days+" days "+hours+" hours "+minutes+" minutes left.");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(tv1.getText().toString().equals("2018-10-02 10:00:00"))
                    b1.setVisibility(View.VISIBLE);
            }
        },10);
        /*LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        String time = localTime.toString();
        String date = localDate.toString();*/
        /*Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tv1.setText(LocalTime.now().toString());
                if(LocalDate.now().equals("2018-10-02") && LocalTime.now().equals("10.30.00.000")) {
                    b1.setVisibility(View.VISIBLE);
                }
            }
        });
        t.start();*/

        //tv1.setText(date);
    }
    public void startQuiz(View v) {
        Intent i=new Intent();
        i.setComponent(new ComponentName(Activity_home.this,DisplayQuiz.class));
        startActivity(i);
    }
}
