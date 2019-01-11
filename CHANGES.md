# SDK versions

In this section you can find what has changed from version to version

## 3.11
* ```DrivitStatusManager``` may now return a new missing setting id: ```SETTING_SIM_CARD_ABSENT```
in cases where no SIM card is available on the user's device. Your app should handle
this situation with the appropriate localized strings as in the remaining settings.
## 3.10.4
## 3.7.10-alpha
* ```CheckAndSyncManager#syncAccelAndTrips``` now receives a boolean as argument that tells the SDK if it should sync trip classification data
* New callback method ```DrivitBroadcastReceiver#onInvalidGoogleApiKey``` that informs the app when the given api key is invalid
* Method ```areLocationsAndEventsAvailable``` was refactored to ```areLocationsAndEventsLocallyAvailable```. Its objective remains the same
* Several minor bug fixes and improvements

