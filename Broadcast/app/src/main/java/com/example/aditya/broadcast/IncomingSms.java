package com.example.aditya.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Aditya on 1/26/2017.
 */

public class IncomingSms extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages;
        String strMessage = "";
        if(bundle != null){
            Object[] pdus = (Object[])bundle.get("pdus");
            if(pdus!=null && pdus.length > 0) {
                messages = new SmsMessage[pdus.length];
                for (int i = 0; i < messages.length; i++) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");
                        Log.v("format", format);
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                    } else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                    Log.v("format", messages[i] + "\n");
                    strMessage += "SMS From: " + messages[i].getOriginatingAddress();
                    strMessage += " : ";
                    strMessage += messages[i].getMessageBody();
                    strMessage += "\n";
                }
            }
            Log.v("SMS", strMessage);
            Toast.makeText(context, strMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
