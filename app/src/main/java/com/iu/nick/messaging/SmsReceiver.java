package com.iu.nick.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle myBundle = intent.getExtras();

        SmsMessage[]myMsgs = null;
        String myStr = "";

        if(myBundle != null){
            Object[] myPdus = (Object[])myBundle.get("pdus");

            myMsgs = new SmsMessage[myPdus.length];

            for(int i = 0; i<myMsgs.length; i++){
                myMsgs[i] = SmsMessage.createFromPdu((byte[])myPdus[i]);
                myStr += "SMS from " + myMsgs[i].getDisplayMessageBody().toString()
                        + "\n";
            }
            Toast.makeText(context, myStr,Toast.LENGTH_SHORT).show();
        }

    }


}
