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

        final SharedPreferences sharedPref = this.getPreferences(
                Context.MODE_PRIVATE);

        Intent intent = getIntent();




        final TextView from = (TextView)findViewById(R.id.from);
        final TextView to = (TextView)findViewById(R.id.to);
        final TextView cc = (TextView)findViewById(R.id.cc);
        final TextView subject = (TextView)findViewById(R.id.subjet);
        final TextView body = (TextView)findViewById(R.id.body);

        from.setText(sharedPref.getString("from",""));
        to.setText(sharedPref.getString("to",""));
        cc.setText(sharedPref.getString("cc",""));
        subject.setText(sharedPref.getString("subject",""));
        body.setText(sharedPref.getString("body",""));
    }







    public void back(View view) {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
