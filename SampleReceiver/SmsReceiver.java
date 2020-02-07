package org.androidtown.samplereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class SmsReceiver extends BroadcastReceiver {
    public static final String TAG = "SmsReceiver";
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:Ss");

    @Override
    //onReceive()는 SMS를 받으면 자동으로 호출된다!!
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReive() 메소드 호춣됨.");

        Bundle bundle = intent.getExtras();  //번들 객체를 getExtras로 참조
        SmsMessage[] messages = parseSmsMessage(bundle);  //메세지 객체 생성, parseSmsMessage는 직접 정의한 메소드

        if(messages != null && messages.length > 0){
            String sender = messages[0].getOriginatingAddress(); //발신자 번호 확인
            Log.i(TAG, "SMS sender : "+sender);

            String contents = messages[0].getMessageBody().toString();  //문자 내용 확인
            Log.i(TAG, "SMS contents : "+contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis());  //문자 받은 시각 확인
            Log.i(TAG, "SMS received date : "+receivedDate);

            sendToActiviy(context, sender, contents, receivedDate);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for (int i=0; i<smsCount; i++){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;
    }

    private void sendToActiviy(Context context, String sender, String contents, Date receiveDate){
        Intent myIntent = new Intent(context, SmsActivity.class);

        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        myIntent.putExtra("sender",sender);
        myIntent.putExtra("contents",contents);
        myIntent.putExtra("receiveDate",format.format(receiveDate));

        context.startActivity(myIntent);
    }
}
