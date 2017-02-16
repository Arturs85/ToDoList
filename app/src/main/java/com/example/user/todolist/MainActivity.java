package com.example.user.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Ieraksts> listItems;
    ListView listView;
    MArrayAdapter arrayAdapter;
EditText editTextMeklet;
    ForegroundColorSpan foregroundColorSpan =new ForegroundColorSpan(0xFFFFFF00);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        listView = (ListView) findViewById(R.id.listView);
        listItems = new ArrayList<>();
        arrayAdapter = new MArrayAdapter(this, R.layout.list_item, R.id.textViewToDoTeksts, listItems);
        listView.setAdapter(arrayAdapter);
        listItems.add(new Ieraksts("Uztaisīt ToDo programmu", 3));
        listItems.add(new Ieraksts("Izdomāt spēli ", 3));
        listView.setOnItemClickListener(onItemClickListener);
        editTextMeklet = (EditText)findViewById(R.id.editTextMeklet);
        editTextMeklet.addTextChangedListener(textWatcher);

    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            atvertIerakstu(listItems.get(position), position);
        }
    };

    private void atvertIerakstu(Ieraksts ieraksts, int position) {
        Intent i = new Intent(this, IerakstaSatursActivity.class); //(TweetTestActivity.this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("param1", ieraksts);
        bundle.putInt("position", position);
        i.putExtras(bundle);
        this.startActivityForResult(i, 1);

    }
public void mekletIerakstus(CharSequence tekstaFragments){
for(int i=0;i<listItems.size();i++){
    String parmekejamaisTeksts =listItems.get(i).toString().toLowerCase();//lai atrastu arī lielos burtus
    if (parmekejamaisTeksts.contains(tekstaFragments)&&tekstaFragments.length()!=0){
       int match = parmekejamaisTeksts.indexOf(tekstaFragments.toString());
        listView.getChildAt(i).setBackgroundColor(Color.GRAY);
        //((TextView)(listView.getChildAt(i).findViewById(R.id.textViewToDoTeksts))).setTextColor(Color.BLUE);
iekrasotTekstu(((TextView)(listView.getChildAt(i).findViewById(R.id.textViewToDoTeksts))),match,tekstaFragments.length());

    }
    else {
        listView.getChildAt(i).setBackgroundColor(Color.TRANSPARENT); // katreiz maina fona kraasu - LABOT
       // Gara rinda
       nonemtIekrasojumu(((TextView) (listView.getChildAt(i).findViewById(R.id.textViewToDoTeksts))));
       // ((TextView) (listView.getChildAt(i).findViewById(R.id.textViewToDoTeksts))).setTextColor(Resources.getSystem().getColor(android.R.color.tertiary_text_dark));
}

}


}
    void iekrasotTekstu(TextView textView,int sakumaPozicija,int garums){

        Spannable spanText = Spannable.Factory.getInstance().newSpannable(textView.getText());
        spanText.setSpan(foregroundColorSpan, sakumaPozicija, sakumaPozicija+garums, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanText);
    }
void nonemtIekrasojumu(TextView textView){
    String t= new String(textView.getText().toString());
    textView.setText(t);
}
    public void onPievienotJaunuClick(View v) {
        Ieraksts jaunsIeraksts = new Ieraksts("", 0);
        listItems.add(jaunsIeraksts);
        int position = listItems.indexOf(jaunsIeraksts);
        atvertIerakstu(jaunsIeraksts, position);
    }
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.e("Main", "onTextChanged"+s);
mekletIerakstus(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("Main", "onActivityResult" + resultCode);
        if (resultCode != RESULT_CANCELED) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Ieraksts ieraksts = (Ieraksts) bundle.getSerializable("param1");
                int position = bundle.getInt("position");
                if (ieraksts != null) {          // atjauno ieraksta datus ierakstu sarakstā
                    Log.e("Main", listItems.get(position).toString());

                    if (ieraksts.toString().isEmpty())
                        listItems.remove(position); // dzēš ierakstu ja teksts ir tukšs
                    else
                        listItems.get(position).setText(ieraksts.toString());
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
