# 📲 **Firebase Cloud Messaging**
📦 Firebase Cloud Messaging Android Sample
This project is a simple Android application that demonstrates the usage of Firebase Cloud Messaging (FCM) to:

📩 Subscribe to a topic (Weather)

🔐 Retrieve the device's FCM token

🔔 Create a notification channel (for Android 8+)

📦 Log and display received FCM data payload

📱 Screenshots
(📌 If available, you can add screenshots of your app UI here.)

🚀 Features
One-click subscription to a Firebase topic

Get and log the FCM device token

Toast messages for user feedback

Logs FCM message payloads sent from Firebase Console

Compatible with Android 8 (API 26) and higher

🛠️ Tech Stack
Language: Java

Firebase: Cloud Messaging

UI: Button-based interaction (XML layout)

Minimum SDK: API 21 (Lollipop)

Target SDK: API 34
🔧 Setup Instructions
Clone the repository:

bash
Copy
Edit
git clone https://github.com/YourUsername/Firebase-Cloud-Messaging.git
Open the project in Android Studio.

Connect your project to Firebase:

Go to Tools > Firebase > Cloud Messaging > Set up Firebase Cloud Messaging

Download google-services.json and place it in app/

Sync Gradle files.

Run the app on an Android device (or emulator with Google Play services).

🔘 How It Works
📌 Notification Channel (Android 8+)
Creates a notification channel to ensure notifications show up correctly on newer Android versions.

java
Copy
Edit
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
   // Channel created here
}
📬 Subscribing to Topic
When you press the first button, your device subscribes to the Weather topic.

java
Copy
Edit
FirebaseMessaging.getInstance().subscribeToTopic("Weather")
🔐 Getting FCM Token
When you press the second button, your device token is retrieved and shown via Toast.

java
Copy
Edit
FirebaseMessaging.getInstance().getToken()
📥 Receiving Data Payload
If the app is opened from a notification with data, it logs the payload:

java
Copy
Edit
getIntent().getExtras()
📤 Sending a Test Notification
You can use Firebase Console to send a notification:

Target: Topic

Topic name: Weather

Add custom data if needed

✅ To Do (Optional Ideas)
Handle messages in background (FirebaseMessagingService)

Add custom UI for notifications

Save token on your backend server

📄 License
This project is open-source and free to use for learning purposes.


