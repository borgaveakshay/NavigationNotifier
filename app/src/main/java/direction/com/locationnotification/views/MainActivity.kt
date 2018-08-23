package direction.com.locationnotification.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import direction.com.locationnotification.R
import direction.com.locationnotification.Util.LocationRequestConstants
import direction.com.locationnotification.databinding.ActivityMainBinding
import direction.com.locationnotification.dependencies.components.DaggerLocationComponent
import direction.com.locationnotification.dependencies.components.LocationComponent
import direction.com.locationnotification.dependencies.modules.LocationModule

class MainActivity : AppCompatActivity() {

    lateinit var mActivityMainBinding: ActivityMainBinding
    lateinit var mLocationComponent: LocationComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mLocationComponent = DaggerLocationComponent.builder().locationModule(LocationModule(this)).build()

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mActivityMainBinding.locationModel = mLocationComponent.getLocationViewModel()
        mActivityMainBinding.locationViewObserver = mLocationComponent.getLocationViewObservable()

        lifecycle.addObserver(mLocationComponent.getLocationViewObservable())

        checkLocationPermission()


    }

    fun checkLocationPermission() {

        when {

            Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP ->
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    startLocationTracking()

                } else {
                    ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            LocationRequestConstants.LOCATION_PERMISSION)
                }
            else -> startLocationTracking()

        }

    }

    fun startLocationTracking() {
        mLocationComponent.getLocationViewObservable().startLocationTracking()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {

            LocationRequestConstants.LOCATION_PERMISSION -> if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                startLocationTracking()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LocationRequestConstants.PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                mLocationComponent.getLocationViewModel().destinationPlace = PlaceAutocomplete.getPlace(this, data)
                mLocationComponent.getLocationViewModel().destinationLocation = PlaceAutocomplete.getPlace(this, data).name as String
                mLocationComponent.getLocationViewModel().notifyChange()
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {


            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

}
