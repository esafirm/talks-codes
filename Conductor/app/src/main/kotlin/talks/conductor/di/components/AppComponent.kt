package talks.conductor.di.components

import android.app.Application
import dagger.Component
import talks.conductor.di.modules.AppModule
import talks.conductor.di.modules.ContextServiceModule
import talks.conductor.di.modules.NetworkModule
import talks.conductor.network.ApiService
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ContextServiceModule::class,
        NetworkModule::class
))
interface AppComponent {

    /* --------------------------------------------------- */
    /* > Singletons */
    /* --------------------------------------------------- */

    fun apiService(): ApiService

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    fun activityComponent(): ActivityComponent.Builder

    companion object {
        fun initialize(app: Application): AppComponent =
                DaggerAppComponent.builder()
                        .appModule(AppModule((app)))
                        .build()
    }
}
