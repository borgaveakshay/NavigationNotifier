package direction.com.locationnotification.observables

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.location.LocationManager
import android.view.View
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import direction.com.locationnotification.Util.LocationRequestConstants
import direction.com.locationnotification.Util.LocationUpdateListener
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity
import javax.inject.Inject


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

    fun buttonClicked(view: View) {

        if (locationViewModel.buttonText.equals("Start")) {
            activity.startLocationService()
            locationViewModel.buttonText = "Stop"
        } else {
            activity.stopLocationTracking()
            locationViewModel.buttonText = "Start"
            locationViewModel.destinationLocation = "Please tap to choose destination"
            locationViewModel.destinationPlace = null
        }
        locationViewModel.notifyChange()

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