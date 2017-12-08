package talks.conductor.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import talks.conductor.navigator.AppNavigator
import talks.conductor.navigator.IntentNavigator

@Module
class NavigatorModule(private val navigator: AppNavigator) {

    @Provides
    fun provideAppNavigator(): AppNavigator = navigator

    @Provides
    fun provideIntentNavigator(context: Context): IntentNavigator = IntentNavigator(context)
}
