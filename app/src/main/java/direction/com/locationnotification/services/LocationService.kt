package direction.com.locationnotification.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import direction.com.locationnotification.R
import direction.com.locationnotification.Util.LocationRequestConstants
import direction.com.locationnotification.Util.NotificationConstants
import direction.com.locationnotification.observables.LocationViewObservable
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity
import javax.inject.Inject


class LocationService @Inject constructor(val locationViewObservable: LocationViewObservable?,
                                          val locationViewModel: LocationViewModel?) : Service() {


    constructor() : this(null, null)

    lateinit var notification: Notification
    override fun onCreate() {
        super.onCreate()
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when {
            intent?.action == LocationRequestConstants.START_LOCATION_FOREGROUND_SERVICE -> {

                val notificationIntent = Intent(this, MainActivity::class.java)
                val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

                val notificationBuilder = Notification.Builder(this)

                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                    notificationBuilder.setChannelId(NotificationConstants.CHANNEL_ID)
                } else {
                    notificationBuilder.setContentTitle("Location Tracking On")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentIntent(pendingIntent)
                }
                notification = notificationBuilder.build()


                locationViewObservable?.startLocationTracking()

                startForeground(LocationRequestConstants.NOTIFICATION_ID, notification)
            }

            intent?.action == LocationRequestConstants.STOP_LOCATION_FOREGROUND_SERVICE -> {
                stopForeground(true)
                stopSelf()
            }
        }

        return START_STICKY
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}