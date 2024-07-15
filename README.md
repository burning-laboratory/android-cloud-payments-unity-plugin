<p align="center">
    <img src="https://static.burning-lab.com/android-plugins/com.burninglab.cpunityplugin/readme-logo.png" alt="Project Logo" width="1106">
</p>

<p align="center">
    <img src="https://build.burning-lab.com/app/rest/builds/buildType:id:UnityPlugins_CloudPayments_Android_DevelopmentBuild/statusIcon.svg" alt="Build Status">
    <a href="https://tasks.burning-lab.com/agiles/131-107/current?settings"><img src="https://img.shields.io/badge/Roadmap-YouTrack-orange" alt="Roadmap Link"></a>
    <img src="https://img.shields.io/badge/34-darkgreen?logo=android&label=Target%20SDK" alt="License">
    <img src="https://img.shields.io/badge/License-MIT-success" alt="License">
</p>

## About

An add-on to the Cloud Payments SDK library for convenient use in Unity applications.

## Dependencies:

List of [EDM4U](https://github.com/googlesamples/unity-jar-resolver.git) dependencies for plugin.

```xml
<?xml version="1.0" encoding="utf-8"?>
<dependencies>
    <androidPackages>

        <androidPackage spec="androidx.appcompat:appcompat:1.6.1"/>
        <androidPackage spec="org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1"/>
        
        <androidPackage spec="ru.cloudpayments.gitpub.integrations.sdk:cloudpayments-android:[1.5.2]">
            <androidSdkPackageIds>
                <androidSdkPackageId>cloudpayments-android</androidSdkPackageId>
            </androidSdkPackageIds>
            <repositories>
                <repository>https://jitpack.io/</repository>
            </repositories>
        </androidPackage>

        <androidPackage spec="com.burning-lab:yoo-kassa-unity-plugin:[1.0.0]">
            <androidSdkPackageIds>
                <androidSdkPackageId>yoo-kassa-unity-plugin</androidSdkPackageId>
            </androidSdkPackageIds>
            <repositories>
                <repository>https://[GITHUB_USERNAME]:[GITHUB_USER_TOKEN]@maven.pkg.github.com/burning-laboratory/android-yoo-kassa-plugin/</repository>
                
            </repositories>
        </androidPackage>
        
    </androidPackages>
</dependencies>
```

## Usage:

### Start tokenization process:

Start tokenization process from Unity application example.

```csharp
    public void RunTokenization()
    {
        // Configure your payment request.
        // See payment request samples in Examples section.
        string paymentRequest = "{}";

        AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
        AndroidJavaObject currentActivity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");

        // Call tokenization process from ui thread.
        currentActivity.Call("runOnUiThread", new AndroidJavaRunnable(() =>
        {
            using (AndroidJavaObject yooKassaUnityPluginActivity = new AndroidJavaObject("com.burninglab.cpunityplugin.CloudPaymentsUnityPluginActivity"))
            {
                AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
                AndroidJavaObject currentActivity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");

                // Call start tokenization plugin method.
                yooKassaUnityPluginActivity.Call("startPayment", currentActivity, paymentRequest);
            }
        }));
    }
```

## Examples

### Payment Request



## License

Project **Burning-Lab.CloudPaymentsUnityPlugin** is distributed under the MIT license.