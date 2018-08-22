package direction.com.locationnotification.dependencies.modules

import android.databinding.PropertyChangeRegistry
import dagger.Module
import dagger.Provides
import direction.com.locationnotification.dependencies.scopes.PropertyRegistryScope

@Module
class PropertyChangeRegistryModule {

    @Provides
    @PropertyRegistryScope
    fun getPropertyChangeRegistry(): PropertyChangeRegistry = PropertyChangeRegistry()

}