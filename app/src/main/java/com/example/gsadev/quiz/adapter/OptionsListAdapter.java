package com.example.gsadev.quiz.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gsadev.quiz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSA Dev on 2/21/2018.
 */

public class OptionsListAdapter extends BaseAdapter {
    private List<List<String>> options=new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    private int index;

     OptionsListAdapter(List<List<String>> options, Context context,int index){
        this.options=options;
        this.index=index;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return options.get(index).size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View layout=mLayoutInflater.inflate(R.layout.option_items,viewGroup,false);

        TextView option=layout.findViewById(R.id.option);
        option.setText(options.get(index).get(position));

        return layout;
    }
}
