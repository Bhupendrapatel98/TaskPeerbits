package com.example.recyclerviewkotlinapplication.firebase

import android.app.Notification.*
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.recyclerviewkotlinapplication.MainActivity
import com.example.recyclerviewkotlinapplication.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context

import org.jetbrains.annotations.NotNull

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseMessagingServ"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "onMessageReceived: ${remoteMessage.data["body"]}")
        Log.d(TAG, "onMessageReceived: ${remoteMessage.notification?.title}")

            /*sendNotification(remoteMessage.notification?.body.toString())*/
            sendNotification(remoteMessage.data["body"].toString())
    }

    private fun sendNotification(message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelid: String = getString(R.string.default_notification_channel_id)
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        //builder
        val notificationBuilder = NotificationCompat.Builder(this, channelid)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("hiiiiiii")
            .setContentText(message)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setWhen(System.currentTimeMillis())
            // .setSound(defaultSoundUri)
            .setDefaults(DEFAULT_ALL)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        //channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelid,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.description = "Welcome"
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.lockscreenVisibility = VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(1, notificationBuilder.build())
    }

}