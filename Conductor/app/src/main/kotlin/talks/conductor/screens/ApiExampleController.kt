package talks.conductor.screens

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import io.reactivex.android.schedulers.AndroidSchedulers
import talks.conductor.R
import talks.conductor.commons.AbsController
import talks.conductor.network.ApiService
import javax.inject.Inject

class ApiExampleController : AbsController() {

    @BindView(R.id.main_txt_hello) lateinit var txtTitle: TextView

    @Inject lateinit var apiService: ApiService

    override fun onSetupComponent() {
        component.inject(this)
    }

    override fun getLayoutResId(): Int = R.layout.controller_main

    override fun onViewBound(bindingResult: View, savedState: Bundle?) {
        txtTitle.setOnClickListener {
            apiService.getUserAgent()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { (userAgent), _ ->
                        txtTitle.text = userAgent
                    }
        }
    }
}
