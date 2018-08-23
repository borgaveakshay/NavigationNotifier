package direction.com.locationnotification.Util

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity
import javax.inject.Inject

class LocationUpdateListener @Inject constructor(val activity: MainActivity,
                                                 val locationViewModel: LocationViewModel,
                                                 val geoCoderService: GeoCoderService) : LocationListener {

    override fun onLocationChanged(currentLocation: Location?) {

        currentLocation?.let { geoCoderService.fetchAddress(currentLocation, activity, locationViewModel) }
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {
    }
}