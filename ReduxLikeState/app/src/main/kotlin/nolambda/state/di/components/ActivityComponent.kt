package nolambda.state.di.components

import dagger.Subcomponent
import nolambda.state.di.modules.ActivityModule
import nolambda.state.di.modules.NavigatorModule
import nolambda.state.di.scopes.ActivityScope
import nolambda.state.navigator.AppNavigator
import nolambda.state.navigator.IntentNavigator

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
