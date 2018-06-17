# AndroidSDK-Sample

This is a sample project to demonstrate the Drivit Android SDK integration. This document also outlines the key steps to integrate Drivit SDK into your application and put it to work.

## Get an API_KEY
To use the Drivit SDK you need an API Key. Contact us at support@drivit.com to get one. 

## Setup your Android Project
### 1. Add the following dependency to your module-level build.gradle file
```
dependencies {
    implementation 'com.github.drivitapp:AndroidSDK:2.0.4'
}

```
### 2. Add the following repository to your project-level build.gradle file
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
### 3. Make sure your app is already running on Java 8
The SDK makes use of features enabled by Java 8. Just add the **compileOptions** option as in the example bellow.
```
android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.drivit.androidsdk_sample"
        minSdkVersion 16
        targetSdkVersion 23
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

With these three steps, your app should be already compiling the Drivit SDK. Now let's put it to work

## Using the SDK
### 1. Add the API Key to the manifest
Add the following piece of code to your app's manifest inside the **<application/>** block
```
<meta-data
     android:name="com.drivit.API_KEY"
     android:value="YOUR_API_KEY" />
 ```
 ### 2. Extend the DrivitApplication class
 **DrivitApplication** provides a way for you to define notifications that the Drivit SDK has to show to the user in certain situations, e.g. when recording a trip. Just create your application class as in the example bellow...
 ```
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
    public Notification getRecordingNotification_OBD(boolean b, boolean b1) {
        return null;
    }

    @Override
    public Notification getAdquiringLocationNotification_OBD() {
        return null;
    }

    @Override
    public Notification getDetected_ConnectingNotification_OBD() {
        return null;
    }

    @Override
    public BroadcastReceiver getAppReceiver() {
        return null;
    }
}
 ```
... and set it in the manifest:
```
<application
        android:name=".MyApplication"
        ...
```
If you don't set any notifications, the SDK will use the default ones.
### 3. Login/signup your user into the SDK
You have to login the user into the SDK before it starts recording trips. To do so, create an instance of the ```DrivitLoginSignupOperation``` object and provide it with the info of your user

```
DrivitLoginSignupOperation login = new DrivitLoginSignupOperation();
login.doLogin(context, "mail@mail.com", "password", null, new LoginListener() {
     @Override
     public void onCompleted(boolean codeOk, int cause, DrivitUser appUser) {
          Toast.makeText(context, "Login completed: " + codeOk + ", cause: " + cause, Toast.LENGTH_SHORT).show();
     }
});
```
And that is it! Safe trips!

**The Drivit Team**
