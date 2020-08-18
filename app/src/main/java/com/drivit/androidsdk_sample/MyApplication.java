package com.drivit.androidsdk_sample;

import android.app.Notification;
import android.content.BroadcastReceiver;

import com.drivit.core.DrivitApplication;
import com.drivit.core.DrivitSession;
import com.drivit.core.DrivitUser;

public class MyApplication extends DrivitApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        /*Uncomment to enable debug mode*/
        DrivitSession drivitSession = DrivitSession.getInstance();
        drivitSession.enableDebug();
    }

    @Override
    public Notification getSuspectNotification(String channelId) {
        return null;
    }

    @Override
    public Notification getRecordingNotification(boolean isSystemOkForRecording, String channelId) {
        return null;
    }

    @Override
    public Notification getProcessingNotification(String channelId) {
        return null;
    }

    @Override
    public BroadcastReceiver getAppReceiver() {
        return null;
    }
}
