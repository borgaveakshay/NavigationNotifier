package direction.com.locationnotification.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import direction.com.locationnotification.R
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


    }
}
