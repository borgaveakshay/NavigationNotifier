package direction.com.locationnotification.dependencies.components

import dagger.Component
import direction.com.locationnotification.dependencies.modules.LocationModule
import direction.com.locationnotification.dependencies.scopes.LocationScope
import direction.com.locationnotification.observables.LocationViewObservable
import direction.com.locationnotification.viewmodels.LocationViewModel

@LocationScope
@Component(modules = [LocationModule::class])
interface LocationComponent {

    fun getLocationViewModel(): LocationViewModel
    fun getLocationViewObservable(): LocationViewObservable

}