package com.example.practicenotifications

import com.google.firebase.messaging.RemoteMessage

import com.google.firebase.messaging.FirebaseMessagingService
import android.R
import android.app.Notification
import androidx.core.app.NotificationCompat
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build

import androidx.core.app.NotificationManagerCompat

const val CHANNEL_NAME = "mybeautip"
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //token을 서버로 전송
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val notificationManager = NotificationManagerCompat.from(
            applicationContext
        )
        var builder: NotificationCompat.Builder? = null
        builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
            }
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            NotificationCompat.Builder(applicationContext)
        }
        val title = remoteMessage.notification!!.title
        val body = remoteMessage.notification!!.body
        builder.setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.checkbox_off_background)
        val notification: Notification = builder.build()
        notificationManager.notify(1, notification)
    }
}