package com.example.user.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by user on 2017.02.08..
 */
public class IerakstaSatursActivity extends Activity {
    Ieraksts ieraksts;
    TextView ierakstaTekstsView;
   int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ieraksta_saturs);


initialize();
    }
void initialize(){

    ierakstaTekstsView = (TextView)findViewById(R.id.ierakstaTekstsEditView);

    Bundle bundle = this.getIntent().getExtras();

    ieraksts = (Ieraksts) bundle.getSerializable("param1");
    position = bundle.getInt("position");
    ierakstaTekstsView.setText(ieraksts.toString());

}
public void OnClickSaveButton(View v){
    ieraksts.setText(ierakstaTekstsView.getText().toString());
saglabatIerakstu(position);
}
private void saglabatIerakstu(int position){

    Intent i = new Intent(this,MainActivity.class); //(TweetTestActivity.this, MapsActivity.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("param1", ieraksts);
    bundle.putInt("position", position);
    i.putExtras(bundle);
   //PendingIntent atbilde =  createPendingResult(1, i, PendingIntent.FLAG_ONE_SHOT);
setResult(RESULT_OK,i);
finish();
}
public void onClickDzestButton(View v){
    ieraksts.setText("");
    saglabatIerakstu(position);
}
}
