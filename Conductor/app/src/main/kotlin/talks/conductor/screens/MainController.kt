package talks.conductor.screens

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import talks.conductor.R
import talks.conductor.commons.AbsController
import talks.conductor.navigator.AppNavigator
import javax.inject.Inject

class MainController : AbsController() {

    @Inject lateinit var navigator: AppNavigator

    @BindView(R.id.main_txt_hello) lateinit var textView: TextView

    override fun getLayoutResId(): Int = R.layout.controller_main

    override fun onViewBound(bindingResult: View, savedState: Bundle?) {
        component.inject(this)

        textView.setOnClickListener {
            navigator.goToDetail()
        }
    }
}
