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

        <androidPackage spec="com.burning-lab:cpunityplugin:[1.1.0]">
            <androidSdkPackageIds>
                <androidSdkPackageId>cpunityplugin</androidSdkPackageId>
            </androidSdkPackageIds>
            <repositories>
                <repository>https://maven.pkg.github.com/burning-laboratory/android-cloud-payments-unity-plugin</repository>
                
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
            using (AndroidJavaObject cpUnityPluginActivity = new AndroidJavaObject("com.burninglab.cpunityplugin.CloudPaymentsUnityPluginActivity"))
            {
                AndroidJavaClass unityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
                AndroidJavaObject currentActivity = unityPlayer.GetStatic<AndroidJavaObject>("currentActivity");

                // Call start tokenization plugin method.
                cpUnityPluginActivity.Call("startPayment", currentActivity, paymentRequest);
            }
        }));
    }
```

## Examples

### Payment Request
Serialized payment request example.
```json
{
	"authData": {
		"publicId": "pk_2092fac1c28490f3c7a035ad26c39"
	},
	"payerInfo": {
		"firstName": "Payer first name",
		"lastName": "Payer last name",
		"middleName": "Payer middle name",
		"birthDay": "01.01.2000",
		"address": "Payer address. Example: 24486 Yukon Rd, Kasilof, Alaska 99610, USA",
		"street": "Payer street. Example: 24486 Yukon Rd",
		"city": "Kasilof",
		"country": "USA",
		"phone": "Payer mobile phone number. Example: +0 (907) 262-6261",
		"postCode": "Payer adress post code. Example: 99610",
		"accountId": "payer_account_unique_id",
		"email": "support@burning-lab.com"
	},
	"invoiceInfo": {
		"invoiceId": "test_invoice",
		"amount": 100.0,
		"currencyCode": "RUB",
		"invoiceDescription": "Invoice description."
	},
	"responseConfig": {
		"callbackObjectName": "Unity Callback Object Name",
		"callbackMethodName": "Unity Callback Method Name"
	}
}
```

### Payment response

Example of payment operation serialized response.

```json
{
	"status": true,
	"errorCode": 0
}
```

## Handle payment operation result

Plugin use Unity JNI system for sending serialized payment operation response to your event method.
Your can read more information for unity JNI system on [this](https://docs.unity3d.com/530/Documentation/Manual/PluginsForAndroid.html) page.

### Payment operation result handler example

```csharp
public void OnPaymentOperationCompleteEventHandler(string serializedPaymentOperationResponse){

}
```

## License

Project **Burning-Lab.CloudPaymentsUnityPlugin** is distributed under the MIT license.