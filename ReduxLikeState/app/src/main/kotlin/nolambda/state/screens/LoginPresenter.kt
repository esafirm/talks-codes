package nolambda.state.screens

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginPresenter @Inject constructor() {

    private val subject = BehaviorSubject.create<LoginState>()

    fun getObservable() = subject.observeOn(AndroidSchedulers.mainThread())!!

    fun login(username: String, password: String) {
        verifyForm(username, password)
                .switchMap { requestLoginApi(username, password) }
                .onErrorReturn {
                    when (it) {
                        is FormError -> ShowFormError(it)
                        else -> ShowError(it)
                    }
                }
                .map(this::reduce)
                .subscribe { subject.onNext(it) }
    }

    // We just want to simulate the asynchronicity
    private fun requestLoginApi(username: String, password: String) =
            Observable.timer(5, TimeUnit.SECONDS)
                    .map { ShowLoginSuccess as LoginAction }
                    .startWith(ShowLoading)

    // For simplicity, we just want to verify if the username and password is not blank
    private fun verifyForm(username: String, password: String) =
            Observable.fromCallable {
                val usernameError = when (username.isBlank()) {
                    true -> "Username cannot be empty"
                    false -> null
                }

                val passwordError = when (password.isBlank()) {
                    true -> "Password cannot be empty"
                    false -> null
                }

                if (usernameError != null || passwordError != null) {
                    throw FormError(usernameError, passwordError)
                } else {
                    Unit
                }
            }


    private fun reduce(action: LoginAction): LoginState {
        val prevState = subject.value ?: LoginState()
        return when (action) {
            ShowLoading -> prevState.copy(isLoading = true, error = null, formError = null)
            ShowLoginSuccess -> prevState.copy(isLoading = false, isLoginSuccess = true)
            is ShowError -> prevState.copy(isLoading = false, error = action.error)
            is ShowFormError -> prevState.copy(isLoading = false, formError = action.formError)
        }
    }
}
