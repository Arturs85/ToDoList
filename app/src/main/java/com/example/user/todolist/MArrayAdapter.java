package com.example.user.todolist;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2017.02.08..
 */
public class MArrayAdapter extends ArrayAdapter {
    public MArrayAdapter (Context context,  int resource, @IdRes int textViewResourceId ,List<Ieraksts> objects){
       super(context,resource,textViewResourceId,objects);
    }
@Override
public View  getView(int position, View convertView, ViewGroup parent){
    View view=   super.getView(position,convertView,parent);

    TextView pievienosanasDatums = (TextView)view.findViewById(R.id.datumsTextView);
pievienosanasDatums.setText("Pievienots: "+((Ieraksts)(super.getItem(position))).getDate());

return view;
    }

}
