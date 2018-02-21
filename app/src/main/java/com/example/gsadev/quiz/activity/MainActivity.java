package com.example.gsadev.quiz.activity;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gsadev.quiz.R;
import com.example.gsadev.quiz.adapter.ViewPagerItemAdapter;
import com.example.gsadev.quiz.view.CustomDialog;
import com.example.gsadev.quiz.view.CustomViewPager;
import com.example.gsadev.quiz.view.DepthPageTransformer;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public CustomViewPager customViewPager;
    private TextView questionCount;
    private TextView timer;
    private CustomDialog alert;
    private ViewPagerItemAdapter adapter;
    private Handler handler = new Handler();
    private static int counter = 10;
    private static boolean appWasInBackGround=false;
    private boolean isRunning=false;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            counter--;
            timer.setText("Timer 00:"+new DecimalFormat("00").format(counter));
            startTimer();
            isRunning=true;
            if(counter==0){
                cancelTimer();
                //+1 because getCurrentItem index start to 0
                int position=customViewPager.getCurrentItem()+1;
                if (position<adapter.getCount()){
                    alert.showDialog(MainActivity.this, getString(R.string.session),getString(R.string.next_quiz));
                } else{
                    if (adapter.correctAnswerCount>adapter.getCount()/2){
                        alert.showDialog(MainActivity.this,
                                getString(R.string.winner),
                                getString(R.string.restart_quiz));
                    }else{
                        alert.showDialog(MainActivity.this,
                                getString(R.string.looser),
                                getString(R.string.restart_quiz));

                    }
                }

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customViewPager=findViewById(R.id.custom_view_pager);
        questionCount=findViewById(R.id.question_count);
        timer=findViewById(R.id.timer_quiz);

        adapter=new ViewPagerItemAdapter(this);
        customViewPager.setAdapter(adapter);
        customViewPager.addOnPageChangeListener(this);
        customViewPager.setOffscreenPageLimit(1);
        customViewPager.setPageTransformer(false,new DepthPageTransformer());

        alert = new CustomDialog();
       if (!isRunning)
        alert.showDialog(MainActivity.this, getString(R.string.start_quiz),getString(R.string.start));
    }


    public void startTimer() {
        handler.postDelayed(runnable, 1000);
    }

    public void cancelTimer() {
        counter=10;
        handler.removeCallbacks(runnable);
        isRunning=false;
    }

    @Override
    protected void onStop() {
        if (isRunning) {
            handler.removeCallbacks(runnable);
            appWasInBackGround=true;
        }
        super.onStop();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (appWasInBackGround){
            startTimer();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        questionCount.setText("Question- "+(position+1)+"/"+customViewPager.getAdapter().getCount());
        timer.setText("Timer 00:"+new DecimalFormat("00").format(counter));
    }

    @Override
    public void onPageSelected(int position) {
        startTimer();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
