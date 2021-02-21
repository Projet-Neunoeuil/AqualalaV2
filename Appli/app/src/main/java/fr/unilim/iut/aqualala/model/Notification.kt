package fr.unilim.iut.aqualala.model

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Notification constructor(val notifId: Int, val channelID: String, var classe: Class<*>, val context: Context){

    fun createNotificationChannel(name: String, descriptionText: String, importance : Int) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = name
            val descriptionText = descriptionText
            val importance = importance
            val channel = NotificationChannel(channelID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun sendNotification(icon: Int, notifTitre: String, notifTexte: String, priorite: Int) {
        // Ouvrir l'activité à partir de la notification
        val intent = Intent(context.applicationContext, classe).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context.applicationContext, 0, intent, 0)

        var notification = NotificationCompat.Builder(context.applicationContext, channelID)
            .setSmallIcon(icon)
            .setContentTitle(notifTitre)
            .setContentText(notifTexte)
            .setContentIntent(pendingIntent)
            .setPriority(priorite)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context.applicationContext)) {
            // notificationId is a unique int for each notification that you must define
            notify(notifId, notification.build())
        }
    }

}