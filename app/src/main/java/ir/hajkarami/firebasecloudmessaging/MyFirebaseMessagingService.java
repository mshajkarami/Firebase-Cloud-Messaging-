package ir.hajkarami.firebasecloudmessaging;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String Tag = "MyFirebaseMessagingService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        Log.v(Tag, "Received message from: " + message.getFrom());
        if (message.getData().size() > 0) {
            Log.v(Tag, "Data payload: " + message.getData());
            if (true) {
                scheduleJob();
            } else {
                handleNow();
            }
        }
        // Check if message contains a notification payload.
        if (message.getNotification() != null) {
            Log.v(Tag, "Notification body: " + message.getNotification().getBody());
        }
    }


    @Override
    public void onNewToken(@NonNull String token) {
        Log.v(Tag, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }


    private void scheduleJob() {
        // [START dispatch_job]
        OneTimeWorkRequest work = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        WorkManager.getInstance(this).beginWith(work).enqueue();
        // [END dispatch_job]
    }

    private void handleNow() {
        Log.v(Tag, "Short lived task is done.");
    }

    private void sendRegistrationToServer(String token) {

    }

    private void sendNotification(String messageBody) {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        String chanelId = getString(R.string.default_notification_channel_Id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, chanelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Hello Welcome To Use this App")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(chanelId, "Channel Human readable Title", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, builder.build());
    }

}