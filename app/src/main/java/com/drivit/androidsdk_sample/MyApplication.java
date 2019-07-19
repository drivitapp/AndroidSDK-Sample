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
    public Notification getRecordingNotification_OBD(boolean b, boolean b1) {
        return null;
    }

    @Override
    public Notification getAdquiringLocationNotification_OBD() {
        return null;
    }

    @Override
    public Notification getDetected_ConnectingNotification_OBD() {
        return null;
    }

    @Override
    public BroadcastReceiver getAppReceiver() {
        return null;
    }
}
