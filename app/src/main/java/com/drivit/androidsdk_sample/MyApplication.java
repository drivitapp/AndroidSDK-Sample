package com.drivit.androidsdk_sample;

import android.app.Notification;
import android.content.BroadcastReceiver;

import com.drivit.core.DrivitApplication;

public class MyApplication extends DrivitApplication {

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
