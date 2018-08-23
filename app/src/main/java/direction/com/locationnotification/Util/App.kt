package direction.com.locationnotification.Util

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import direction.com.locationnotification.R


class App : Application() {

    lateinit var notificationChannel: NotificationChannel
    lateinit var destinationNotificationChannel: NotificationChannel

    companion object {
        lateinit var appContext: App
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this
        createChannel()
    }

    fun createChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelName = getString(R.string.notification_channel)
            val destinationChannel = getString(R.string.destination_notification_channel)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            notificationChannel = NotificationChannel(NotificationConstants.CHANNEL_ID, channelName, importance)
            destinationNotificationChannel = NotificationChannel(NotificationConstants.LOCATION_REACHED_CHANNEL_ID, destinationChannel, importance)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.createNotificationChannel(destinationNotificationChannel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun notifyUserAboutReachingDestination() {

        val notificationBuilder = Notification.Builder(this)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

            notificationBuilder.setChannelId(NotificationConstants.LOCATION_REACHED_CHANNEL_ID)
        }
        val notification = notificationBuilder
                .setAutoCancel(true)
                .setContentTitle("You are reaching your destination")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)


    }
}