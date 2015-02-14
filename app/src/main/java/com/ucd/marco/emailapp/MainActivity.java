package com.ucd.marco.emailapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_FROM = "com.ucd.marco.emailapp.FROM";
    public final static String EXTRA_TO = "com.ucd.marco.emailapp.TO";
    public final static String EXTRA_CC = "com.ucd.marco.emailapp.CC";
    public final static String EXTRA_SUBJECT = "com.ucd.marco.emailapp.SUBJECT";
    public final static String EXTRA_BODY = "com.ucd.marco.emailapp.BODY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SharedPreferences sharedPref = this.getPreferences(
                 Context.MODE_PRIVATE);


        Button clear = (Button)findViewById(R.id.clear_button);
        Button send = (Button)findViewById(R.id.send_button);
        final EditText from = (EditText)findViewById(R.id.from);
        final EditText to = (EditText)findViewById(R.id.to);
        final EditText cc = (EditText)findViewById(R.id.cc);
        final EditText bcc = (EditText)findViewById(R.id.bcc);
        final EditText subject = (EditText)findViewById(R.id.subject);
        final EditText body = (EditText)findViewById(R.id.body);

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


                if (isFormValid(from, to, cc, bcc, subject, body)){
                    intent.putExtra(EXTRA_FROM, from.getText().toString());
                    intent.putExtra(EXTRA_TO, to.getText().toString());
                    intent.putExtra(EXTRA_CC, cc.getText().toString());
                    intent.putExtra(EXTRA_SUBJECT, subject.getText().toString());
                    intent.putExtra(EXTRA_BODY, body.getText().toString());
                    startActivity(intent);
                }





            }
        });



    }


    public final boolean isFormValid(EditText from,EditText to,EditText cc,EditText bcc,EditText subject,EditText body) {
        if(!isValidEmail(from.getText(), false)){
            Toast.makeText(getApplicationContext(), R.string.invalid_from_email, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidEmail(to.getText(), false)){
            Toast.makeText(getApplicationContext(), R.string.invalid_to_email, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidEmail(cc.getText(), true)){
            Toast.makeText(getApplicationContext(), R.string.invalid_cc_email, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidEmail(bcc.getText(), true)){
            Toast.makeText(getApplicationContext(), R.string.invalid_bcc_email, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidText(subject.getText())){
            Toast.makeText(getApplicationContext(), R.string.invalid_subject, Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!isValidText(body.getText())){
            Toast.makeText(getApplicationContext(), R.string.invalid_body, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public final static boolean isValidEmail(CharSequence target, boolean optional) {


        List<String> emailList = Arrays.asList(target.toString().split(","));

        boolean isValid = true;

        for (String singleEmail : emailList) {
            if (optional && TextUtils.isEmpty(target)) {
                isValid = true;
            }else if (!optional && TextUtils.isEmpty(singleEmail)) {
                isValid = false;
            } else {
                isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(singleEmail).matches();
            }
        }

        return isValid;


    }

    public final static boolean isValidText(CharSequence target) {
         return !TextUtils.isEmpty(target) ;

    }





    @Override
    protected void onStop() {
        super.onStop();

        final EditText from = (EditText)findViewById(R.id.from);
        final EditText to = (EditText)findViewById(R.id.to);
        final EditText cc = (EditText)findViewById(R.id.cc);
        final EditText bcc = (EditText)findViewById(R.id.bcc);
        final EditText subject = (EditText)findViewById(R.id.subject);
        final EditText body = (EditText)findViewById(R.id.body);

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



        final EditText from = (EditText)findViewById(R.id.from);
        final EditText to = (EditText)findViewById(R.id.to);
        final EditText cc = (EditText)findViewById(R.id.cc);
        final EditText bcc = (EditText)findViewById(R.id.bcc);
        final EditText subject = (EditText)findViewById(R.id.subject);
        final EditText body = (EditText)findViewById(R.id.body);

        from.setText(sharedPref.getString("from",""));
        to.setText(sharedPref.getString("to",""));
        cc.setText(sharedPref.getString("cc",""));
        bcc.setText(sharedPref.getString("bcc",""));
        subject.setText(sharedPref.getString("subject",""));
        body.setText(sharedPref.getString("body",""));

    }




}
