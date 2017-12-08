package talks.conductor.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import talks.conductor.R

class DemoController : Controller() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.controller_demo, container, false).also {

            // Set time info
            it.findViewById<TextView>(R.id.text_caption).text = "Controller #${router.backstackSize}"

            // Bind add button
            it.findViewById<Button>(R.id.button_add).setOnClickListener { addController() }

            // Bind remove button
            it.findViewById<Button>(R.id.button_remove).setOnClickListener { removeController() }

            // Bind set root button
            it.findViewById<Button>(R.id.button_set_root).setOnClickListener { setRoot() }

            // Bind add backstack button
            it.findViewById<Button>(R.id.button_backstack).setOnClickListener { addBackStack() }
        }
    }

    private fun addController() {
        router.pushController(RouterTransaction.with(DemoController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler((HorizontalChangeHandler())))


        RouterTransaction.with(DemoController())
                .pushChangeHandler(HorizontalChangeHandler())
                .popChangeHandler((HorizontalChangeHandler()))


    }

    private fun removeController() {
        router.popCurrentController()
    }

    private fun addBackStack() {
        val newBackstack = router.backstack + (1..10).map {
            RouterTransaction.with(DemoController())
                    .pushChangeHandler(HorizontalChangeHandler())
                    .popChangeHandler(HorizontalChangeHandler())
        }
        router.setBackstack(newBackstack, FadeChangeHandler())
    }

    private fun setRoot() {
        router.setRoot(RouterTransaction.with(DemoController()))
    }

}
