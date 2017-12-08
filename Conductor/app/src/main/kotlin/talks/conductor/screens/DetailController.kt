package talks.conductor.screens

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import talks.conductor.R
import talks.conductor.commons.AbsController
import talks.conductor.navigator.AppNavigator
import javax.inject.Inject

class DetailController : AbsController() {

    @BindView(R.id.main_txt_yeah) lateinit var txtYeah: TextView

    @Inject lateinit var navigator: AppNavigator

    override fun onSetupComponent() {
        component.inject(this)
    }

    override fun getLayoutResId(): Int = R.layout.controller_detail

    override fun onViewBound(bindingResult: View, savedState: Bundle?) {
        txtYeah.setOnClickListener {
            navigator.goToApiExample()
        }
    }
}
