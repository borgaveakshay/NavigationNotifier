package direction.com.locationnotification.observables

import android.arch.lifecycle.LifecycleObserver
import direction.com.locationnotification.viewmodels.LocationViewModel
import direction.com.locationnotification.views.MainActivity

class LocationViewObservable(var locationViewModel: LocationViewModel,
                             val activity: MainActivity) : LifecycleObserver {


}