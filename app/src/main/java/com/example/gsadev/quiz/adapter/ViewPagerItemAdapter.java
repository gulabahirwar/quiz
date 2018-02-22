package com.example.gsadev.quiz.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.gsadev.quiz.R;
import com.example.gsadev.quiz.activity.MainActivity;
import com.example.gsadev.quiz.data.QuizData;
import com.example.gsadev.quiz.view.CustomDialog;

/**
 * Created by GSA Dev on 2/21/2018.
 */

public class ViewPagerItemAdapter extends PagerAdapter implements View.OnClickListener {
    private QuizData quizData;
    private  LayoutInflater mLayoutInflater;
    public   int correctAnswerCount=0;
    private  View previousSelectedItem;
    private  Context context;



    public ViewPagerItemAdapter(Context context){
        this.context=context;
         mLayoutInflater=LayoutInflater.from(context);

        quizData=new QuizData();
        quizData.setQuestions();
        quizData.setOptions();
        quizData.setAnswers();
    }

    @Override
    public int getCount() {
        return quizData.getQuestions().size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        ViewGroup layout = (ViewGroup) mLayoutInflater.inflate(R.layout.quiz_items, container, false);

        final TextView questions=layout.findViewById(R.id.quiz_question);
        final Button nextQuiz=layout.findViewById(R.id.next_quiz);
        ListView options=layout.findViewById(R.id.quiz_option);

        questions.setText(quizData.getQuestions().get(position));

        options.setAdapter(new OptionsListAdapter(quizData.getOptions(),context,position));
        options.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (previousSelectedItem!=null) {
                    previousSelectedItem.setBackgroundColor(Color.WHITE);
                }
                previousSelectedItem=view;

                view.setBackground(context.getResources().getDrawable(R.drawable.rect));

                //+1 because i index start to 0
                if (quizData.getAnswers().get(position)==i+1){
                    correctAnswerCount+=1;
                }
            }
        });
        if(position+1==getCount()){
            nextQuiz.setText("View Result");
        }
        nextQuiz.setOnClickListener(this);

        container.addView(layout);
        return layout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public void onClick(View view) {

        MainActivity activity=(MainActivity)context;
        //+1 because getCurrentItem index start to 0
        int pos=(activity).customViewPager.getCurrentItem()+1;
        activity.cancelTimer();
        if (pos<getCount()){
              activity.customViewPager.setCurrentItem(pos);
        }else{
            CustomDialog alert=new CustomDialog();
            if (correctAnswerCount>getCount()/2){
                alert.showDialog(activity,
                        context.getString(R.string.winner),
                        context.getString(R.string.restart_quiz));
            }else{
                alert.showDialog(activity,
                        context.getString(R.string.looser),
                        context.getString(R.string.restart_quiz));
            }
        }


    }
    }

