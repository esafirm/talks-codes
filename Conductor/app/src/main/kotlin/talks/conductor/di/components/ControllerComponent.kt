package talks.conductor.di.components

import dagger.Subcomponent
import talks.conductor.di.modules.ControllerModule
import talks.conductor.screens.ApiExampleController
import talks.conductor.screens.DetailController
import talks.conductor.screens.MainController

@Subcomponent(modules = arrayOf(ControllerModule::class))
interface ControllerComponent {

    /* --------------------------------------------------- */
    /* > Injects */
    /* --------------------------------------------------- */

    fun inject(mainController: MainController)
    fun inject(detailController: DetailController)
    fun inject(apiExampleController: ApiExampleController)

    /* --------------------------------------------------- */
    /* > Builders */
    /* --------------------------------------------------- */

    @Subcomponent.Builder
    interface Builder {
        fun controllerModule(controllerModule: ControllerModule): ControllerComponent.Builder
        fun build(): ControllerComponent
    }
}
