# Change log

In this section you can find what has changed from version to version

## 5.3.0-beta1
* Adds new WLTP properties on the `Report` object
* New fuel type id `VehicleType#FUELTYPE_ETHANOL` that can be used to get ethanol-based vehicles
* The SDK now returns different defaults depending on the region of the user, namely the fuel price for the base vehicle, the electricity price
associated with each charging rule, and the default electric vehicle
* Minor bug fixes 

## 5.3.0-beta
* Adds new WLTP properties on the `VehicleDetails` object
* Minor bug fixes
## 5.2.0
* Minor bug fixes
## 5.2.0-alpha2
This is an alpha release that should not be used in production. Changes from previous version:
* The SDK now returns different results as a function of the region of the user as set by `DrivitLoginSignupOperation#setRegion()`. In particular, in the APIs related to vehicle setup
* `VehicleDetails#getDisplacement()` was deprecated. Use `VehicleDetails#getEngineDisplacement()` instead
* `VehicleDetails#getPower()` was deprecated. Use `VehicleDetails#getEnginePower()` instead
* Minor bug fixes and internal improvements
## 5.2.0-alpha1
This is an alpha release that should not be used in production. Changes from previous version:
* Two unnecessary user permissions were removed from the SDK
* `DrivitCloud#fallbackCheck()` has been deprecated and can be safely removed
* New method `DrivitLoginSignupOperation#setRegion()` to set a geographic region in each signup
* The method `VehicleSettings#setFuelPrice(double,boolean)` was simplified to `VehicleSettings#setFuelPrice(double)`. You can safely remove the boolean argument
* The deprecated method `DrivitUser#getTrips(Context)` was removed. Use `DrivitUser#getTrips()` instead
* Several internal improvements and bug fixes
## 4.2.6.6
* Minor bug fixes and other improvements
## 4.2.6.5
* Drivit is now fully compatible with the new Android 10 (Q), in particular with the new location permissions system. 
You should update the permissions strings in your app (related with the `DrivitStatusManager`) to reflect this change.
Please contact us when you do so
* As such, the Drivit SDK is now compiled with `compileSdkVersion` 29. You may have to update your app's gradle as well
* The ids of trip classification (e.g. `TripType#VALIDITY_REJECTED_BY_USER_OTHER`) were refactored to better reflect
their nature. They are now prefixed with 'CLASSIFICATION' instead of 'VALIDITY'
* `SETTING_HUAWEI_BACKGROUND_PERMISSION_NOUGAT` was refactored to `SETTING_HUAWEI_BACKGROUND_NOUGAT`. Its purpose remains the same
* `SETTING_HUAWEI_BACKGROUND_PERMISSION_OREO_AND_ABOVE` was refactored to `SETTING_HUAWEI_BACKGROUND_OREO_AND_ABOVE`. Its purpose remains the same
## 3.12.11
* Fixes communication issues between the SDK and Google APIs. These issues were already dealt with from version 4.0.0 onwards. The 
purpose of this release is for apps where 4.0.0+ was not integrated yet and therefore a fix should be released to production with minimum changes
## 4.1.3
* The Drivit SDK is now fully compatible with `targetSdkVersion` 28. You may configure your gradle accordingly. 
Upgrading to `targetSdkVersion` 28 may impact your app outside the scope of our SDK. For more information,
please check the Google documentation, in particular the [target API level requirements](https://developer.android.com/distribute/best-practices/develop/target-sdk#prepie). 
Please take your time to test your app before releasing a new upgraded version to the Play Store. 
* If necessary, you can release a new version of your app to the Play Store with this SDK version without upgrading to
`targetSdkVersion` 28, contact our team to know more
* Core Google libraries that Drivit uses were updated
* Minor bug fixes and improvements
## 4.0.0
* New class ```DrivitSession``` that controls behaviors of the Drivit SDK in each memory session
* The method ```DrivitSettings#enableTripClassification()``` is now accessed through ```DrivitSession```
* The method ```DrivitSettings#enableDebug()``` is now accessed through ```DrivitSession```
* ```DrivitStatusManager``` may now return a new missing setting id ```SETTING_XIAOMI_BACKGROUND_PERMISSION```
for some Xiaomi devices where a special permission should be given. Your app should handle
this situation with the appropriate localized strings as in the remaining settings
* ```DrivitStatusManager``` may now return a new missing setting id ```SETTING_HUAWEI_BACKGROUND_PERMISSION_NOUGAT```
for some Huawei devices where a special permission should be given. Your app should handle
this situation with the appropriate localized strings as in the remaining settings
* A new method ```DebugSession#debugCustomPermission(int)``` was created. Calling this method with a custom permission id from
```DrivitStatusManager``` will enable you to debug the implementation of custom permissions provided by the SDK by returning them
in any device as if they were missing
* 1st phase of preparations to increase the ```targetSdkVersion``` to 28 ahead of the November 2019 deadline
* Several trip recording improvements
* Several battery consumption improvements
* Several minor bug fixes and other improvements 
## 3.12.8
* Fixes communication issues between the SDK and the Drivit servers
* Minor bug fixes
## 3.12.4.1
* ```DrivitStatusManager``` may now return a new missing setting id ```SETTING_SIM_CARD_ABSENT```
in cases where no SIM card is available on the user's device. Your app should handle
this situation with the appropriate localized strings as in the remaining settings
* ```DrivitStatusManager``` may now return a new missing setting id ```SETTING_AIRPLANE_ACTIVATED```
in cases where the air plane mode is activated. Your app should handle
this situation with the appropriate localized strings as in the remaining settings
* ```DrivitStatusManager``` may now return a new missing setting id ```SETTING_SAMSUNG_UNMONITORED```
in specific Samsungs models which have a custom restriction that should be removed by the user. Your app should handle
this situation with the appropriate localized strings as in the remaining settings
* Several minor bug fixes and improvements
## 3.10.4
* New method ```DrivitSettings.enableTripClassification()``` that explicitly tells the SDK if it should compute the trip classification metrics.
Should be called on your Application ```onCreate()``` method. If your app is not using trip classification, you should ignore this method
* If trip classification is enabled, ```DrivitStatusManager``` will automatically start
requiring an additional permission from your users (Overlay permission). Your app
should handle the appropriate localized strings as in all other settings
* The field ```guid``` from the object ```TripType``` is now private. Should you need access to it,
use the method ```TripType#getGuid()```
* Improvements in the SDK battery consumption
* Several minor bug fixes and improvements
## 3.7.10-alpha
* ```CheckAndSyncManager#syncAccelAndTrips``` now receives a boolean as argument that tells the SDK if it should sync trip
classification data. If your app is not using classification, you should use false as argument
* New optional callback method ```DrivitBroadcastReceiver#onInvalidGoogleApiKey``` that informs the app when the given google api key is invalid
* Method ```areLocationsAndEventsAvailable``` was refactored to ```areLocationsAndEventsLocallyAvailable```. Its objective remains the same
* Several minor bug fixes and improvements

