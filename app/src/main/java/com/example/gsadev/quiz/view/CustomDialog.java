package com.example.gsadev.quiz.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gsadev.quiz.R;
import com.example.gsadev.quiz.activity.MainActivity;
import com.example.gsadev.quiz.adapter.ViewPagerItemAdapter;

/**
 * Created by GSA Dev on 2/21/2018.
 */

public class CustomDialog {
        public void showDialog(final Activity activity, String msg, final String buttonText){
            final Dialog dialog = new Dialog(activity);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.custom_dialog);

            TextView text = (TextView) dialog.findViewById(R.id.msg_dialog);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setText(buttonText);

            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (buttonText.equalsIgnoreCase("start")){
                        ((MainActivity)activity).startTimer();
                    } else  if (buttonText.equalsIgnoreCase("restart quiz")){
                        ((MainActivity)activity).customViewPager.setAdapter(new ViewPagerItemAdapter(activity));
                        ((MainActivity)activity).startTimer();
                    }else{
                        ((MainActivity)activity).customViewPager.setCurrentItem(
                                ((MainActivity)activity).customViewPager.getCurrentItem()+1);
                    }

                    dialog.dismiss();
                }
            });

            dialog.show();

        }
}
