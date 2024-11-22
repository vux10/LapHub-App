package com.example.appbanhang.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.appbanhang.R;
import com.example.appbanhang.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessengerReceiver extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if (message.getNotification() != null){
            Log.d("FirebaseMessage", "Notification received: " + message.getNotification().getTitle() + " - " + message.getNotification().getBody());
            showNotification(message.getNotification().getTitle(), message.getNotification().getBody());
        } else {
            Log.d("FirebaseMessage", "Message received without notification payload");
        }
    }

    private void showNotification(String title, String body){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String channelId = "noti";
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.baseline_cloud_queue_24)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);
        builder = builder.setContent(customView(title, body));
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "web_app", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
            Log.d("NotificationChannel", "Notification channel created: " + channelId);
        }
        if (notificationManager != null) {
            Log.d("NotificationManager", "Notification Manager is ready to show notification.");
            notificationManager.notify(1, builder.build());
        } else {
            Log.e("NotificationManager", "Notification Manager is null!");
        }

    }

    private RemoteViews customView(String title, String body){
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notification);
        remoteViews.setTextViewText(R.id.title_noti, title);
        remoteViews.setTextViewText(R.id.body_noti, body);
        remoteViews.setImageViewResource(R.id.imgnoti, R.drawable.baseline_cloud_queue_24);
        return remoteViews;
    }
}
