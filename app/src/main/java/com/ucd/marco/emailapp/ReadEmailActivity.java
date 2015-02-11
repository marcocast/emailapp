package com.ucd.marco.emailapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ReadEmailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_email);

        Intent intent = getIntent();


        final TextView from = (TextView)findViewById(R.id.from);
        final TextView to = (TextView)findViewById(R.id.to);
        final TextView cc = (TextView)findViewById(R.id.cc);
        final TextView subject = (TextView)findViewById(R.id.subject);
        final TextView body = (TextView)findViewById(R.id.body);



        from.setText(intent.getStringExtra(MainActivity.EXTRA_FROM));
        to.setText(intent.getStringExtra(MainActivity.EXTRA_TO));
        cc.setText(intent.getStringExtra(MainActivity.EXTRA_CC));
        subject.setText(intent.getStringExtra(MainActivity.EXTRA_SUBJECT));
        body.setText(intent.getStringExtra(MainActivity.EXTRA_BODY));
    }



    public void back(View view) {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
