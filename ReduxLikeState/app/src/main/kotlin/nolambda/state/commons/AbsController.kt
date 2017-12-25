package nolambda.state.commons

import com.esafirm.conductorextra.butterknife.BinderController
import nolambda.state.di.components.ActivityComponent
import nolambda.state.di.components.ControllerComponent
import nolambda.state.di.helpers.HasComponent
import nolambda.state.di.modules.ControllerModule

abstract class AbsController : BinderController() {

    @Suppress("UNCHECKED_CAST")
    protected val component: ControllerComponent by lazy {
        if (activity == null) {
            throw IllegalStateException("Not attached to Activity")
        }
        if ((activity is HasComponent<*>).not()) {
            throw IllegalStateException("Activity should implement HasComponent<Component>")
        }
        activity.let { it as HasComponent<ActivityComponent> }
                .getComponent()
                .controllerComponent()
                .controllerModule(ControllerModule(this))
                .build()
    }
}
