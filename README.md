# AndroidSDK-Sample

This is a sample project to demonstrate the Drivit Android SDK integration. This document also outlines the key steps to integrate Drivit SDK into your application and put it to work.

## Get an API_KEY
To use the Drivit SDK you need an API Key. Contact us at support@drivit.com to get one. 

## Setup your Android Project
### 1. Add the following dependency to your module build.gradle file
```
dependencies {
    implementation 'com.github.drivitapp:AndroidSDK:1.0.0'
}

```
### 2. Add the following repository to your project build.gradle file
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
    compileSdkVersion 27
    defaultConfig {
        applicationId "com.drivit.androidsdk_sample"
        minSdkVersion 16
        targetSdkVersion 27
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
Add the following piece of code to your app's manifest 
```
<meta-data
            android:name="com.drivit.API_KEY"
            android:value="YOUR_API_KEY" />
 ```
