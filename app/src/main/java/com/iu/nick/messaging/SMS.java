package com.iu.nick.messaging;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.ACTION_SEND;

public class SMS extends AppCompatActivity {

    private EditText mPhone;
    private EditText mMessage;
    private EditText mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        mPhone = (EditText) findViewById(R.id.number);
        mMessage = (EditText) findViewById(R.id.message);
        mEmail = (EditText) findViewById(R.id.email);
    }

        public void sendOnClick(View v){
            if(mPhone.getText().toString().length()==0){
                Toast.makeText(this, "Enter a Phone  Number", Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(mPhone.getText().toString(), null, mMessage.getText().toString(),
                            null, null);
                    Toast.makeText(this, "We sent SMS", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(this, "We did not send SMS", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
            }
    }
        public void smsIntentOnClick(View v){
            if(mPhone.getText().toString().length()==0){
                Toast.makeText(this, "Enter a Phone  Number", Toast.LENGTH_SHORT).show();
            }
            else {
                try {
                    Intent sentIntent = new Intent(Intent.ACTION_VIEW);
                    sentIntent.putExtra("address", mPhone.getText().toString());
                    sentIntent.putExtra("sms_body", mMessage.getText().toString());
                    sentIntent = sentIntent.setType("vnd.android-dir/mms-sms");
                    startActivity(sentIntent);
                } catch (Exception e) {
                    Toast.makeText(this, "We did not send SMS", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
    }
        public void sendEmailOnClick(View v){
            String emailAddress = mEmail.getText().toString();
            String msgBody = mMessage.getText().toString();

            if(mMessage.length() != 0 && mEmail.length() != 0) {
                Intent emailIntent = new Intent(ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
                emailIntent.putExtra(Intent.EXTRA_TEXT, msgBody);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EMAIL FROM MY APP!");
                try{
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    mEmail.setText("");
                    mMessage.setText("");

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Problem sending email", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please enter both email addtress and message", Toast.LENGTH_SHORT).show();
            }
        }
}

