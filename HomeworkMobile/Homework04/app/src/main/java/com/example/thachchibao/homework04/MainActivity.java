package com.example.thachchibao.homework04;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.todddavies.components.progressbar.ProgressWheel;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    Button btstart;
    Button btcancel;
    ProgressWheel pw;
    Cookie cookie;

    Monter mon1;
    Monter mon2;
    GandmaMonter mama;
    int seconds;
    boolean go;
    CountDownTimer timer;
    boolean show;
    boolean start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.cookie1);
        tv2 = (TextView)findViewById(R.id.cookie2);
        tv3 = (TextView)findViewById(R.id.cookie3);
        tv4 = (TextView)findViewById(R.id.tv4);
        progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressBar3);
        btstart = (Button)findViewById(R.id.btstart);
        btcancel = (Button)findViewById(R.id.btcancel);
        pw = (ProgressWheel) findViewById(R.id.pw_spinner);
        cookie = new Cookie(0);
        mon1 = new Monter("monter 1",cookie);
        mon2 = new Monter("monter 2",cookie);
        mama = new GandmaMonter("mama",cookie);
        go = true;
        show = true;
        start = true;


        tv1.setText("0");
        tv2.setText("0");
        tv3.setText("0");
        tv4.setText("Simulation Clock: 0/120");
        progressBar1.setProgress(0);
        progressBar2.setProgress(0);

        timer = new CountDownTimer(121000, 1000) {

            public void onTick(long millisUntilFinished) {
                seconds = (int) millisUntilFinished / 1000;
                seconds = 120 - seconds;
                tv4.setText("Simulation Clock: "+seconds+"/120");
                progressBar3.setProgress(seconds);
                // case 1 and stop thread

            }

            public void onFinish() {
                seconds=120;
                tv4.setText("Simulation Clock: "+seconds+"/120");
                progressBar3.setProgress(seconds);
                pw.stopSpinning();

                go = false;

                mon1.interrupt();
                mon2.interrupt();
                mama.interrupt();

                if(mon1.amount > mon2.amount){
                    Toast.makeText(MainActivity.this,"Monter 1 WIN!!!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Monter 2 WIN!!!",Toast.LENGTH_SHORT).show();
                }



            }
        };


        // end setup
        // end setup

        btstart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                pw.startSpinning();

                tv1.setText("0");
                tv2.setText("0");
                tv3.setText("0");
                tv4.setText("Simulation Clock: 0/120");
                progressBar1.setProgress(0);
                progressBar2.setProgress(0);


                if(start == true){
                    start = false;
                    mon1.start();
                    mon2.start();
                    mama.start();
                    timer.start();
                }else{

                    if(go==true){

                        Toast.makeText(MainActivity.this,"Game is running",Toast.LENGTH_SHORT).show();

                    }else {

                        cookie = new Cookie(0);
                        mon1 = new Monter("monter 1",cookie);
                        mon2 = new Monter("monter 2",cookie);
                        mama = new GandmaMonter("mama",cookie);

                        go=true;

                        mon1.start();
                        mon2.start();
                        mama.start();
                        timer.start();
                    }


                }

            }
        });

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start==false){
                    pw.stopSpinning();
                    timer.cancel();
                    go=false;
                    mon1.interrupt();
                    mon2.interrupt();
                    mama.interrupt();

                }
            }
        });

    }



    class Monter extends Thread{
        private Thread t;
        private String threadName;
        Cookie  vcookie;
        private int amount;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        Monter( String name,  Cookie cook) {
            threadName = name;
            vcookie = cook;
            amount = 0;
        }

        public void run() {

            while (go == true){
                synchronized (vcookie){
                    amount = amount + vcookie.takeCookie();
                    if(threadName == "monter 1"){
                        new job1().execute(amount);
                    }else {
                        new job2().execute(amount);
                    }
                }
                int max = 5;
                int min = 1;
                Random rand = new Random();
                final int time = rand.nextInt((max - min) + 1) + min; // random from 1 to 5
                try {
                    //thread to sleep for the specified number of milliseconds
                    Thread.sleep(time*1000);
                } catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
            }
        }

        public void start () {

            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }


    }




    class GandmaMonter extends Thread {
        private Thread t;
        private String threadName;
        Cookie  vcookie;


        GandmaMonter( String name,  Cookie cook) {
            threadName = name;
            vcookie = cook;
        }

        public void run() {
            while (go == true){

                synchronized (vcookie){
                    vcookie.bakeCookie();
                    new job3().execute(cookie.getCookie());
                }
                try {
                    //thread to sleep for the specified number of milliseconds
                    Thread.sleep(5000);

                } catch ( java.lang.InterruptedException ie) {
                    System.out.println(ie);
                }
            }
        }

        public void start () {

            if (t == null) {
                t = new Thread (this, threadName);
                t.start ();
            }
        }


    }


    class job1 extends AsyncTask<Integer,String,Integer>{

        @Override
        protected Integer doInBackground(Integer... params) {
            Integer t = params[0];
            if(t>=100){
                timer.cancel();
                go=false;
                mon1.interrupt();
                mon2.interrupt();
                mama.interrupt();

            }
            return t;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            tv1.setText(String.valueOf(result));
            progressBar1.setProgress(result);
            tv3.setText(String.valueOf(cookie.getCookie()));
            if(result >= 100){
                Toast.makeText(MainActivity.this,"Monter 1 WIN!!!",Toast.LENGTH_SHORT).show();
                pw.stopSpinning();
            }
        }
    }

    class job2 extends AsyncTask<Integer,String,Integer>{

        @Override
        protected Integer doInBackground(Integer... params) {
            Integer t = params[0];
            if(t>=100){
                timer.cancel();
                go=false;
                mon1.interrupt();
                mon2.interrupt();
                mama.interrupt();

            }
            return t;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            tv2.setText(String.valueOf(result));
            progressBar2.setProgress(result);
            tv3.setText(String.valueOf(cookie.getCookie()));
            if(result >= 100){
                Toast.makeText(MainActivity.this,"Monter 2 WIN!!!",Toast.LENGTH_SHORT).show();
                pw.stopSpinning();
            }
        }
    }

    class job3 extends AsyncTask<Integer,String,Integer>{

        @Override
        protected Integer doInBackground(Integer... params) {

            Integer t = params[0];
            return t;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);
            tv3.setText(String.valueOf(result));
        }
    }


}
