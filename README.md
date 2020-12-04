# Android SDK Sample

This is a sample project to demonstrate the Drivit Android SDK integration. This document also outlines the key steps to integrate Drivit SDK into your application and put it to work.

## Get an API_KEY
To use the Drivit SDK you need an API Key. Contact us at support@drivit.com to get one. 

## Setup your Android Project
### 1. Add the following dependency to your module-level build.gradle file
```gradle
dependencies {
    implementation 'com.github.drivitapp:android-core:6.0.0'
}

```
### 2. Add the following repository to your project-level build.gradle file
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
### 3. Make sure your app is already running on Java 8
The SDK makes use of features enabled by Java 8. Just add the **compileOptions** option as in the example bellow.
```gradle
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

With these three steps, your app should be already compiling the Drivit SDK. Now let's put it to work

## Using the SDK
### 1. Add the Drivit API Key to the manifest
Add the following piece of code to your app's manifest inside the **<application/>** block
```xml
<meta-data
     android:name="com.drivit.API_KEY"
     android:value="YOUR_API_KEY" />
 ```

### 2. Add relevant Google Maps API Keys
Drivit uses Google APIs to provide an improved user experience. This sample uses two of them:
```xml
<!--Google API Key. Enables the Maps SDK so that trips can be shown on a map. If you choose
other maps supplier, you can ignore this key-->
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_API_KEY" />

<!--Google Api Key. Enables the Google Awareness API which Drivit uses in its inner workings.
You can also use the services enabled by this API in your app, more info here: https://developers.google.com/awareness/overview
You should NOT ignore this key-->
<meta-data
    android:name="com.google.android.awareness.API_KEY"
    android:value="YOUR_API_KEY" />
```


### 3. Extend the DrivitApplication class
**DrivitApplication** provides a way for you to define notifications that the Drivit SDK has to show to the user in certain situations, e.g. when recording a trip. Just create your application class as in the example bellow...
```java
public class MyApplication extends DrivitApplication {

    @Override
    public Notification getSuspectNotification() {
        return null;
    }

    @Override
    public Notification getRecordingNotification(boolean b) {
        return null;
    }

    @Override
    public Notification getProcessingNotification() {
        return null;
    }

    @Override
    public BroadcastReceiver getAppReceiver() {
        return null;
    }
}
 ```
... and set it in the manifest:
```xml
<application
        android:name=".MyApplication"
/>
```
If you don't set any notifications, the SDK will use the default ones.
### 4. Login/signup your user into the SDK
You have to signup/login the user into the SDK before it starts recording trips. 
To do so, create an instance of the ```DrivitLoginSignupOperation``` object and 
provide it with the info of your user. This is how you would login:

```java
DrivitLoginSignupOperation login = new DrivitLoginSignupOperation(context);
login.doLogin(context, "mail@mail.com", "password", new LoginListener() {
     @Override
     public void onCompleted(boolean codeOk, int cause, DrivitUser appUser) {
          
     }
});
```

Or, for extended privacy, just use an encrypted user id:
```java
login.doLogin(context, encryptedUserId, new LoginListener() {
     @Override
     public void onCompleted(boolean codeOk, int cause, DrivitUser appUser) {
          
     }
});
```

### 5. Add a vehicle to the user
All users need to have an associated vehicle to have trips. You need to provide this vehicle to the
SDK so that trips are recorded:
```java
DrivitCloud.getInstance(context).addUserVehicle(vehicle, {...})
```

And that is it! Safe trips!

**The Drivit Team**

<br/><br/>P.S. You can see the complete reference documentation [here](https://javadoc.jitpack.io/com/github/drivitapp/android-core/6.1.1/javadoc/reference/packages.html)
