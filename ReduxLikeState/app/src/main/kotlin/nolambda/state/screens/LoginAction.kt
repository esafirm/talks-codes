package nolambda.state.screens

sealed class LoginAction

object ShowLoading : LoginAction()
object ShowLoginSuccess : LoginAction()
data class ShowError(val error: Throwable) : LoginAction()
data class ShowFormError(val formError: FormError) : LoginAction()
