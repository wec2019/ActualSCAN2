package com.scan.wilian.actualscan;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.scan.wilian.actualscan.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Estimote SDK file that provides class and variables to make beacon notifications

public class BeaconNotificationsManager {

    private static final String TAG = "BeaconNotifications";

    private BeaconManager beaconManager;

    private List<BeaconRegion> regionsToMonitor = new ArrayList<>();
    private HashMap<String, String> enterMessages = new HashMap<>();
    private HashMap<String, String> exitMessages = new HashMap<>();

    private Context context;

    private int notificationID = 0;
    
    //Configures message for notification 
    public BeaconNotificationsManager(Context context) {
        this.context = context;
        beaconManager = new BeaconManager(context);
        //setMonitoringListener monitors for the beacons
        beaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            //Override for entering the beacon range.
            //Logs notes, provides string for message that will be on the notification
            //when entering the beacon range.
            @Override
            public void onEnteredRegion(BeaconRegion region, List<Beacon> list) {
                Log.d(TAG, "onEnteredRegion: " + region.getIdentifier());
                String message = enterMessages.get(region.getIdentifier());
                if (message != null) {
                    showNotification(message);
                }
            }
               
            //Override for exiting the beacon range.
            //Logs notes, provides string for message that will be on the notification
            //when exiting the beacon range.
            @Override
            public void onExitedRegion(BeaconRegion region) {
                Log.d(TAG, "onExitedRegion: " + region.getIdentifier());
                String message = exitMessages.get(region.getIdentifier());
                if (message != null) {
                    showNotification(message);
                }
            }
        });
    }
    
    //Adds the notifications to the beacon
    public void addNotification(BeaconID beaconID, String enterMessage, String exitMessage) {
        BeaconRegion region = beaconID.toBeaconRegion();
        enterMessages.put(region.getIdentifier(), enterMessage);
        exitMessages.put(region.getIdentifier(), exitMessage);
        regionsToMonitor.add(region);
    }
    
    //Initializes bluetooth monitoring of region for beacons
    public void startMonitoring() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                for (BeaconRegion region : regionsToMonitor) {
                    beaconManager.startMonitoring(region);
                }
            }
        });
    }
    //Configures how notification will appear on Android Phone
    private void showNotification(String message) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Beacon Notifications")
                .setContentText(message)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++, builder.build());
    }
}
