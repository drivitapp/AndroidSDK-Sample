# Change log

In this section you can find what has changed from version to version

## 3.12.3
* ```DrivitStatusManager``` may now return a new missing setting id: ```SETTING_SIM_CARD_ABSENT```
in cases where no SIM card is available on the user's device. Your app should handle
this situation with the appropriate localized strings as in the remaining settings
* ```DrivitStatusManager``` may now return a new missing setting id: ```SETTING_AIRPLANE_ACTIVATED```
in cases where the air plane mode is activated. Your app should handle
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

