package com.ucd.marco.emailapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SharedPreferences sharedPref = this.getPreferences(
                 Context.MODE_PRIVATE);


        Button clear = (Button)findViewById(R.id.clear_button);
        Button send = (Button)findViewById(R.id.send_button);
        final TextView from = (TextView)findViewById(R.id.from);
        final TextView to = (TextView)findViewById(R.id.to);
        final TextView cc = (TextView)findViewById(R.id.cc);
        final TextView bcc = (TextView)findViewById(R.id.bcc);
        final TextView subject = (TextView)findViewById(R.id.subjet);
        final TextView body = (TextView)findViewById(R.id.body);

        final Intent intent = new Intent(this, ReadEmailActivity.class);

        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                from.setText("");
                to.setText("");
                cc.setText("");
                bcc.setText("");
                subject.setText("");
                body.setText("");

            }
        });


        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(intent);

            }
        });



    }



    @Override
    protected void onStop() {
        super.onStop();

        final TextView from = (TextView)findViewById(R.id.from);
        final TextView to = (TextView)findViewById(R.id.to);
        final TextView cc = (TextView)findViewById(R.id.cc);
        final TextView bcc = (TextView)findViewById(R.id.bcc);
        final TextView subject = (TextView)findViewById(R.id.subjet);
        final TextView body = (TextView)findViewById(R.id.body);

        final SharedPreferences sharedPref = this.getPreferences(
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("from", from.getText().toString());
        editor.putString("to", to.getText().toString());
        editor.putString("cc", cc.getText().toString());
        editor.putString("bcc", bcc.getText().toString());
        editor.putString("subject", subject.getText().toString());
        editor.putString("body", body.getText().toString());

        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final SharedPreferences sharedPref = this.getPreferences(
                Context.MODE_PRIVATE);



        final TextView from = (TextView)findViewById(R.id.from);
        final TextView to = (TextView)findViewById(R.id.to);
        final TextView cc = (TextView)findViewById(R.id.cc);
        final TextView bcc = (TextView)findViewById(R.id.bcc);
        final TextView subject = (TextView)findViewById(R.id.subjet);
        final TextView body = (TextView)findViewById(R.id.body);

        from.setText(sharedPref.getString("from",""));
        to.setText(sharedPref.getString("to",""));
        cc.setText(sharedPref.getString("cc",""));
        bcc.setText(sharedPref.getString("bcc",""));
        subject.setText(sharedPref.getString("subject",""));
        body.setText(sharedPref.getString("body",""));

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
