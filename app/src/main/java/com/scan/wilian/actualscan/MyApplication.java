package com.scan.wilian.actualscan;

import android.app.Application;


import com.estimote.coresdk.common.config.EstimoteSDK;
import com.scan.wilian.actualscan.BeaconID;
import com.scan.wilian.actualscan.BeaconNotificationsManager;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class MyApplication extends Application {

    private boolean beaconNotificationsEnabled = false;
    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: put your App ID and App Token here
        // You can get them by adding your app on https://cloud.estimote.com/#/apps
        EstimoteSDK.initialize(getApplicationContext(), "<scan2019-5xz>", "<9b3bb15783e59a43d6db81defffa604f>");

        // uncomment to enable debug-level logging
        // it's usually only a good idea when troubleshooting issues with the Estimote SDK
//        EstimoteSDK.enableDebugLogging(true);
    }


    public void enableBeaconNotifications() {
        if (beaconNotificationsEnabled) { return; }

        BeaconNotificationsManager beaconNotificationsManager = new BeaconNotificationsManager(this);
        beaconNotificationsManager.addNotification(
                // TODO: replace with UUID, major and minor of your own beacon
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 995, 40651),
                "Hello, green.",
                "Goodbye, green.");
        beaconNotificationsManager.addNotification(
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 28803, 64459),
                "Hello, world.",
                "Goodbye, world.");
        beaconNotificationsManager.addNotification(
                new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 30351, 55051),
                "Hello, blue.",
                "Goodbye, blue.");
        beaconNotificationsManager.startMonitoring();

        beaconNotificationsEnabled = true;
    }

    public boolean isBeaconNotificationsEnabled() {
        return beaconNotificationsEnabled;
    }
}