package nolambda.state.screens

data class LoginState(
        val isLoading: Boolean = false,
        val error: Throwable? = null,
        val formError: FormError? = null,
        val isLoginSuccess: Boolean = false
)

class FormError(
        val usernameErrorMessage: String? = null,
        val passwordErrorMessage: String? = null
) : Throwable()
