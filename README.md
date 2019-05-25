# SCAN iBeacon Project
The iBeacon files will give a template on how to monitor for iBeacons with an Android phone. The project will need to be made under the owner's Estimote Cloud account
so that the phone can be able to detect and identify the beacons  and perform different actions based on the beacon it connects to.
The application starts by checking if it meets the system requirements based on Estimote's system checker, and then it begins
the monitoring if the phone passed the system check.

## Configuration files
The modified files will all be under the ActualSCAN2/app folder. 
- app/build.gradle
  - This file will have the dependencies needed to get the app working on Android 6.0.1.
  - It also has the Estimote SDK version that needs to be implemented.
- app/src/main/AndroidManifest.xml
  - This file edits the application variables
  - Naming the .MyApplication is important when using variables and classes from MyApplication
- The Java files under app/src/main/java/com/scan/wilian/actualscan/ provide the main functionality of this app.


## Implementation with full SCAN application 
This code was added to the main SCAN application. There were no errors when the application ran but the beacon monitoring function did not run in the background. Even after removing the system requirement checks, it did not run. We attempted to go in and out of range of the beacons but the notification never displayed. We did not attempt to check the logs, it could have been possible that it did detect the beacons, just that the notifications were disabled.

## Authors
- Wilian Chauca
- Juan Sigua
- Wei Xie
