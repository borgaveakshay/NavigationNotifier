package direction.com.locationnotification.Util

import android.content.Context
import android.location.Geocoder
import android.location.Location
import direction.com.locationnotification.viewmodels.LocationViewModel
import java.io.IOException
import java.util.*
import javax.inject.Inject


class GeoCoderService @Inject constructor() : Runnable {

    lateinit var context: Context
    lateinit var location: Location
    lateinit var locationViewModel: LocationViewModel

    override fun run() {
        val geocoder = Geocoder(context, Locale.getDefault())

        try {
            val list = geocoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(), 1)
            if (list != null && list.size > 0) {
                val address = list[0]
                // sending back first address line and locality
                locationViewModel.currentLocation = address.getAddressLine(0) + ", " + address.locality
                locationViewModel.notifyChange()
            }
        } catch (e: IOException) {

        }

    }

    fun fetchAddress(loc: Location, con: Context, locViewModel: LocationViewModel) {
        location = loc
        context = con
        locationViewModel = locViewModel
        Thread(this).start()
    }

}