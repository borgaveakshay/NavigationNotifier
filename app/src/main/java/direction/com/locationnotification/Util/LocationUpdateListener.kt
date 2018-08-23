package direction.com.locationnotification.Util

import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity
import javax.inject.Inject

class LocationUpdateListener @Inject constructor(val activity: MainActivity,
                                                 val locationViewModel: LocationViewModel,
                                                 val geoCoderService: GeoCoderService) : LocationListener {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onLocationChanged(currentLocation: Location?) {

        currentLocation?.let { geoCoderService.fetchAddress(currentLocation, activity, locationViewModel) }
        locationViewModel.destinationPlace?.let {

            val location = Location("")
            location.latitude = it.latLng!!.latitude
            location.longitude = it.latLng.longitude
            val distanceInMeters = currentLocation?.distanceTo(location)

            val distanceInKm = distanceInMeters!! * 0.001

            if (distanceInKm <= 1) {

                App.appContext.notifyUserAboutReachingDestination()
                locationViewModel.buttonText = "Stop"
                locationViewModel.notifyChange()
            }
        }

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {
    }
}