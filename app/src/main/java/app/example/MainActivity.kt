package app.example

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import app.example.circuit.InboxScreen
import app.example.di.ActivityKey
import app.example.di.AppScope
import app.example.ui.theme.ComposeAppTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, boundType = Activity::class)
@ActivityKey(MainActivity::class)
class MainActivity
    @Inject
    constructor(
        private val circuit: Circuit,
    ) : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()

            setContent {
                ComposeAppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        // See https://slackhq.github.io/circuit/navigation/
                        val backStack = rememberSaveableBackStack(root = InboxScreen)
                        val navigator = rememberCircuitNavigator(backStack)

                        // See https://slackhq.github.io/circuit/circuit-content/
                        CircuitCompositionLocals(circuit) {
                            NavigableCircuitContent(
                                navigator = navigator,
                                backStack = backStack,
                                Modifier.padding(innerPadding),
                                decoration =
                                    GestureNavigationDecoration {
                                        navigator.pop()
                                    },
                            )
                        }
                    }
                }
            }
        }
    }
