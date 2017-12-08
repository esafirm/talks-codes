package talks.conductor.di.components

import dagger.Subcomponent
import talks.conductor.di.modules.ActivityModule
import talks.conductor.di.modules.NavigatorModule
import talks.conductor.di.scopes.ActivityScope
import talks.conductor.navigator.AppNavigator
import talks.conductor.navigator.IntentNavigator

@ActivityScope
@Subcomponent(modules = arrayOf(
        ActivityModule::class,
        NavigatorModule::class
))
interface ActivityComponent {

    fun appNavigator(): AppNavigator
    fun intentNavigator(): IntentNavigator

    /* --------------------------------------------------- */
    /* > Subcomponent */
    /* --------------------------------------------------- */

    fun controllerComponent(): ControllerComponent.Builder

    /* --------------------------------------------------- */
    /* > Builder */
    /* --------------------------------------------------- */

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): ActivityComponent.Builder
        fun navigatorModule(navigatorModule: NavigatorModule): ActivityComponent.Builder
        fun build(): ActivityComponent
    }
}
