package nolambda.state.screens

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import com.esafirm.conductorextra.finishActivity
import nolambda.state.R
import nolambda.state.commons.AbsController
import javax.inject.Inject

class LoginController : AbsController() {

    @BindView(R.id.inp_username) lateinit var inpUsername: EditText
    @BindView(R.id.inp_password) lateinit var inpPassword: EditText
    @BindView(R.id.progress) lateinit var progress: View
    @BindView(R.id.btn_login) lateinit var btnLogin: View

    @Inject lateinit var presenter: LoginPresenter

    override fun getLayoutResId(): Int = R.layout.controller_main

    override fun onViewBound(bindingResult: View, savedState: Bundle?) {
        component.inject(this)

        presenter.getObservable().subscribe { state ->
            renderError(state)
            renderLoading(state)
            renderButton(state)
                                             
            if (state.isLoginSuccess) {
                Toast.makeText(applicationContext, "Login Success!", Toast.LENGTH_SHORT)
                        .show()
                finishActivity()
            }
        }

        btnLogin.setOnClickListener {
            presenter.login(
                    username = inpUsername.text.toString(),
                    password = inpPassword.text.toString()
            )
        }
    }

    private fun renderLoading(state: LoginState) {
        progress.visibility = when (state.isLoading) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }
    
    private fun renderButton(state: LoginState) {
        btnLogin.isEnabled = when (state.isButtonEnabled) {
            true -> true
            false -> false
        }
    }

    private fun renderError(state: LoginState) {
        if (state.error != null) {
            Toast.makeText(applicationContext, state.error.message, Toast.LENGTH_SHORT).show()
        }
        state.formError?.run {
            usernameErrorMessage?.let { inpUsername.error = it }
            passwordErrorMessage?.let { inpPassword.error = it }
        }
    }
}
