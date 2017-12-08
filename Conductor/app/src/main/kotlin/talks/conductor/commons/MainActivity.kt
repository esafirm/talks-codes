package talks.conductor.commons

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.internal.LifecycleHandler
import talks.conductor.MainApp
import talks.conductor.demo.ActivityTest
import talks.conductor.di.components.ActivityComponent
import talks.conductor.di.helpers.HasComponent
import talks.conductor.di.modules.ActivityModule
import talks.conductor.di.modules.NavigatorModule
import talks.conductor.navigator.AppNavigator


class MainActivity : AppCompatActivity(), HasComponent<ActivityComponent> {

    companion object {
        const val LAYOUT_ID_MAIN = 0x1
        const val LAYOUT_ID_OVERLAY = 0x2
    }

    private lateinit var router: Router
    private lateinit var overlayRouter: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )

        val main = generateLayout(LAYOUT_ID_MAIN, params)
        val overlay = generateLayout(LAYOUT_ID_OVERLAY, params)

        val lifecycleHandler = LifecycleHandler.install(this)
        router = lifecycleHandler.getRouter(main, savedInstanceState)
        overlayRouter = lifecycleHandler.getRouter(overlay, savedInstanceState)

        router.rebindIfNeeded()
        overlayRouter.rebindIfNeeded()

        setContentView(FrameLayout(this).apply {
            layoutParams = params
            addView(main)
            addView(overlay)
        })

        activityComponent.appNavigator().setupContent()

        startActivity(Intent(this, ActivityTest::class.java))
    }

    private fun generateLayout(layoutId: Int, params: FrameLayout.LayoutParams) =
            ChangeHandlerFrameLayout(this).apply {
                id = layoutId
                layoutParams = params
            }


    override fun onBackPressed() {
        if (!overlayRouter.handleBack() && !router.handleBack()) {
            super.onBackPressed()
        }
    }

    /* --------------------------------------------------- */
    /* > Component */
    /* --------------------------------------------------- */

    override fun getComponent(): ActivityComponent {
        return activityComponent
    }

    private val activityComponent: ActivityComponent by lazy {
        MainApp.appComponent!!
                .activityComponent()
                .activityModule(ActivityModule(this))
                .navigatorModule(NavigatorModule(AppNavigator(router, overlayRouter)))
                .build()
    }
}
