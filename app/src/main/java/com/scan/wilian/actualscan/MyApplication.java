package com.scan.wilian.actualscan;

import android.app.Application;


import com.estimote.coresdk.common.config.EstimoteSDK;
import com.scan.wilian.actualscan.BeaconID;
import com.scan.wilian.actualscan.BeaconNotificationsManager;

//
// Logs into the owner account and identifies the beacons
//

public class MyApplication extends Application {

    private boolean beaconNotificationsEnabled = false;
    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: put your App ID and App Token here
        // You can get them by adding your app on https://cloud.estimote.com/#/apps
        EstimoteSDK.initialize(getApplicationContext(), "<APP ID HERE>", "<Token HEre>");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }


    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this);
        //Notification is created for each beacon
        beaconNotificationsManager.addNotification(
                // TODO: replace with UUID, major and minor of your own beacon
                new BeaconID("UUID", "Major", "Minor"),
                "Hello, green.",
                "Goodbye, green.");
        beaconNotificationsManager.addNotification(
                new BeaconID("UUID", "Major", "Minor"),
                "Hello, purple.",
                "Goodbye, purple.");
        beaconNotificationsManager.addNotification(
                new BeaconID("UUID", "Major", "Minor"),
                "Hello, blue.",
                "Goodbye, blue.");
        beaconNotificationsManager.startMonitoring();

        beaconNotificationsEnabled = true;
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }
}
