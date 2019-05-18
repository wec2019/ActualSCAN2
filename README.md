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

## Authors
- Wilian Chauca
- Juan Sigua
- Wei Xie
