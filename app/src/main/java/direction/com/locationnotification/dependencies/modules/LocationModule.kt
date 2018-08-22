package direction.com.locationnotification.dependencies.modules

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import direction.com.locationnotification.dependencies.scopes.LocationScope
import direction.com.locationnotification.observables.LocationViewObservable
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity

@Module
class LocationModule(val activity: MainActivity) {

    @Provides
    @LocationScope
    fun getMainActivity(): MainActivity = activity

    @Provides
    @LocationScope
    fun getLocationViewModel(activity: MainActivity) = ViewModelProviders.of(activity).get(LocationViewModel::class.java)

    @Provides
    @LocationScope
    fun getLocationViewObservable(activity: MainActivity, locationViewModel: LocationViewModel) = LocationViewObservable(locationViewModel, activity)


}