package com.someone.sendsms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by yeyintkoko on 11/4/16.
 */

public class SendSMS extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private Callback callback = null;

    public SendSMS(ReactApplicationContext reactContext) {

        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "SendSMS";
    }

    private void sendCallback(String message){
        if (callback != null) {
            callback.invoke(message);
            callback = null;
        }
    }

    //---sends an SMS message to another device---
    @ReactMethod
    public void send(String phoneNumber, String message, final Callback cb ){

        try{

            this.callback = cb;

       
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, null,null);

        }catch (Exception e) {

            sendCallback("Unknown error");
            throw e;

        }

    }

}
