package com.zero.callhepler;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.telephony.PhoneStateListener;
import android.util.Log;
import android.view.KeyEvent;

public class CallReceiver extends BroadcastReceiver {
    private static int mState = -1;
    @Override
    public void onReceive(final Context context, Intent intent){
        if (!intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            telephonyManager.listen(new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String incomingNumber){
                    Log.e(" " + state," " +mState);
                    if (mState != state) {
                        mState = state;
                        switch (state) {
                            case TelephonyManager.CALL_STATE_RINGING: //来电
                                if (incomingNumber.equals("XXXXXXXXXXXX")) {
                                    Log.e("接听电话"," "+incomingNumber);
                                    Intent workIntent = new Intent(context, CallWorkActivity.class);
                                    workIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(workIntent);
                                }
                                break;
                            case TelephonyManager.CALL_STATE_OFFHOOK:
                                break;
                            case TelephonyManager.CALL_STATE_IDLE:
                                break;
                        }
                    }
                    super.onCallStateChanged(state, incomingNumber);
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

}
