package direction.com.locationnotification.dependencies.components

import android.databinding.PropertyChangeRegistry
import dagger.Component
import direction.com.locationnotification.dependencies.modules.PropertyChangeRegistryModule
import direction.com.locationnotification.dependencies.scopes.PropertyRegistryScope

@PropertyRegistryScope
@Component(modules = [PropertyChangeRegistryModule::class])
interface PropertyChangeRegistryComponent {

    fun getPropertyChangeRegistry(): PropertyChangeRegistry
}