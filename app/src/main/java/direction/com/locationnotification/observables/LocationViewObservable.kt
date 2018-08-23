package direction.com.locationnotification.observables

import android.arch.lifecycle.LifecycleObserver
import android.location.LocationManager
import android.view.View
import direction.com.locationnotification.Util.LocationRequestConstants
import direction.com.locationnotification.Util.LocationUpdateListener
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity
import javax.inject.Inject
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import android.content.Intent


class LocationViewObservable @Inject constructor(val locationManager: LocationManager,
                                                 val locationListener: LocationUpdateListener,
                                                 var locationViewModel: LocationViewModel,
                                                 val activity: MainActivity) : LifecycleObserver {


    fun chooseDestination(view: View) {

        try {
            val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(activity)
            activity.startActivityForResult(intent, LocationRequestConstants.PLACE_PICKER_REQUEST)
        } catch (e: GooglePlayServicesRepairableException) {
            // TODO: Handle the error.
        } catch (e: GooglePlayServicesNotAvailableException) {
            // TODO: Handle the error.
        }

    }


    fun startLocationTracking() {
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER
                    , LocationRequestConstants.MIN_TIME_DISPLACEMENT
                    , LocationRequestConstants.MIN_DISTANCE_DISPLACEMENT
                    , locationListener)
        } catch (securityException: SecurityException) {

        }
    }

}