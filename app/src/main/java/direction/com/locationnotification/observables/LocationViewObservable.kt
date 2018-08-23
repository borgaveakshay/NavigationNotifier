package direction.com.locationnotification.observables

import android.arch.lifecycle.LifecycleObserver
import android.location.LocationManager
import android.view.View
import direction.com.locationnotification.Util.LocationRequestConstants
import direction.com.locationnotification.Util.LocationUpdateListener
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity
import javax.inject.Inject

class LocationViewObservable @Inject constructor(val locationManager: LocationManager,
                                                 val locationListener: LocationUpdateListener,
                                                 var locationViewModel: LocationViewModel,
                                                 val activity: MainActivity) : LifecycleObserver {


    init {

        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
                    , LocationRequestConstants.MIN_TIME_DISPLACEMENT
                    , LocationRequestConstants.MIN_DISTANCE_DISPLACEMENT
                    , locationListener)
        } catch (securityException: SecurityException) {

        }

    }

    fun chooseDestination(view: View) {


    }


}