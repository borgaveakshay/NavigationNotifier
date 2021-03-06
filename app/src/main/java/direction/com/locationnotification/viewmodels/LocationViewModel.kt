package direction.com.locationnotification.viewmodels

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.google.android.gms.location.places.Place
import direction.com.locationnotification.dependencies.components.DaggerPropertyChangeRegistryComponent
import direction.com.locationnotification.dependencies.components.PropertyChangeRegistryComponent
import javax.inject.Inject

data class LocationViewModel(var currentLocation: String,
                             var destinationLocation: String,
                             var destinationPlace: Place?,
                             var buttonText: String) : Observable, ViewModel() {


    @Inject constructor() : this("Getting current location..."
            , "Please tap to choose destination"
            , null
            , "Start")

    val mPropertyChangeRegistryComponent: PropertyChangeRegistryComponent = DaggerPropertyChangeRegistryComponent.builder().build()
    val mPropertyChangeRegistry: PropertyChangeRegistry = mPropertyChangeRegistryComponent.getPropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

        mPropertyChangeRegistry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

        mPropertyChangeRegistry.add(callback)
    }

    fun notifyChange() {
        mPropertyChangeRegistry.notifyCallbacks(this, 0, null)
    }
}