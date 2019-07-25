# Configurations

Drivit requires certain configurations to properly record user trips. These configurations cover several situations, namely:
* Internal SDK configurations. For example, if the app turns off the recording engine, the SDK will return an error through `SETTING_RECORDING_STATUS`. These configurations are optional and need only to be configured if your app changes the default value;
* Android permissions that are given by default but that the user can turn off at any moment. For example, `SETTING_INTERNET`. These configurations are mandatory and need to be handled by your app;
* Android permissions that are not given by default and that the app must explicitly ask, e.g. `SETTING_LOCATION_RESOLVABLE`. These configurations are mandatory and need to be handled by your app;
* Custom permissions created by specific OEMs, e.g. Huawei. Unlike the above-mentioned situations, for technical reasons, Drivit can't confirm that the user has correctly configured them. 
These configurations are mandatory and need to be handled by your app


It's up to the app to properly inform the user why each configuration is required. Nevertheless, we feel we still have a role to play in helping our clients define the strings
that explain each and every one of them. As such, the following table provides recommended titles and subtitles in English.


<br/><br/>

| Setting id| Name                                                      |Type     | Recommended title for user  | Recommended subtitle for user
| --------- |-----------------------------------------------------------|---------| ----------------------------|----|
| 0         | SETTING_INTERNET                                          |Mandatory|Internet                     |Without internet, the accuracy of your location is weaker and we may have to use GPS more often. In some devices, it may prevent us from recording your trips|
| 2         | SETTING_LOCATION_RESOLVABLE                               |Mandatory|Location                     |Without location, it\'s harder to detect your trips and you won\'t see your routes|
| 3         | SETTING_LOCATION_NOT_RESOLVABLE                           |Mandatory|Location                     |Without location, it\'s harder to detect your trips and you won\'t see your routes|
| 6         | SETTING_XIAOMI_BACKGROUND_PERMISSION                      |Mandatory|Background                   |Confirm that we can run in the background when you lock your phone|
| 7         | SETTING_HUAWEI_POWER_PLAN                                 |Mandatory|Battery plan                 |Confirm you have enabled the performance power plan|
| 8         | SETTING_LOCATION_GPS                                      |Mandatory|Location                     |We avoid using your gps as much as possible. But in some cases where the location accuracy is low, not having gps may affect the recording quality|
| 10        | SETTING_POWER_SAVING                                      |Mandatory|Power saving                 |Turn off power saving to enable Drivit. We can try to show you the way but your device may not let us|
| 11        | SETTING_DOZE                                              |Mandatory|Background                   |Giving this permission enhances the recording quality|
| 12        | SETTING_SIM_CARD_ABSENT                                   |Mandatory|SIM card                     |Without a valid SIM card, your device may restrict Drivit\'s automatic recording|
| 13        | SETTING_AIRPLANE_ACTIVATED                                |Mandatory|Airplane                     |While you have the airplane mode activated, Drivit won\'t record any trip|
| 14        | SETTING_SAMSUNG_UNMONITORED                               |Mandatory|Unmonitored                  |Confirm that Drivit is included in the list of unmonitored apps to avoid restrictions that impact trip recording|
| 15        | SETTING_HUAWEI_BACKGROUND_PERMISSION_NOUGAT               |Mandatory|Background                   |Confirm that we can run in the background when you lock your phone|
| 16        | SETTING_HUAWEI_BACKGROUND_PERMISSION_OREO_AND_ABOVE       |Mandatory|App launch                   |Confirm that the system\'s automatic management option is turned off for Drivit|
| 1         | SETTING_RECORDING_STATUS                                  |Optional |Settings                     |Automatic trips are turned off|
| 5         | SETTING_TEMPORARILY_OFF                                   |Optional |Drivit is paused             |You have paused Drivit due to low battery|
| 9         | SETTING_OVERLAY_PERMISSION                                |Optional |Score                        |By having the overlay permission, we can calculate more precisely the score of your trips|