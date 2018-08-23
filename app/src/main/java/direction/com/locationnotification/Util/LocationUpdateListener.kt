package direction.com.locationnotification.Util

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity
import javax.inject.Inject

class LocationUpdateListener @Inject constructor(val activity: MainActivity,
                                                 val locationViewModel: LocationViewModel) : LocationListener {

    override fun onLocationChanged(currentLocation: Location?) {

        currentLocation?.let { GeoCoderService.fetchAddress(currentLocation, activity, locationViewModel) }

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}