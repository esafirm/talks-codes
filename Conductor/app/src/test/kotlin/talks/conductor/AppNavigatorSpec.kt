package talks.conductor

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import io.kotlintest.mock.mock
import io.kotlintest.specs.StringSpec
import talks.conductor.navigator.AppNavigator
import org.mockito.Mockito

class AppNavigatorSpec : StringSpec({

    val mainRouter = mock<Router>()
    val tested = AppNavigator(mainRouter, mock())

    "Verify setup content" {
        tested.setupContent()
        verify(mainRouter).setRoot(Mockito.any(RouterTransaction::class.java))
    }

})

