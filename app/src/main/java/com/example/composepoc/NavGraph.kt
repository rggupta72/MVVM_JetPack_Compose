package com.example.composepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composepoc.core.common.NavigationEvent
import com.example.composepoc.presentation.viewmodel.DummyArrayViewModel
import jakarta.inject.Inject

class NavGraph : ComponentActivity() {

    @Inject
    lateinit var dummyArrayViewModel: DummyArrayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent()
        {
            NavController()
        }

//        dummyArrayViewModel.productListVewModel.observe(this) {
//            it.getContentIfNotHandled()?.let { navigation ->
//                when (navigation) {
//
//                    is NavigationEvent.XyzNavigation -> {
//
//                    }
//
//                    else -> {
//
//                    }
//
//                }
//            }
//        }


    }
}

@Composable
fun NavController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registration") {
        composable(route = "registration") {
            RegistrationScreen() {
                navController.navigate("login")
            }
        }
        composable(route = "login") {
            LoginScreen() { abc, xyz ->
                navController.navigate("main/${abc}/${xyz}")
            }
        }
        composable(route = "main/{email}/{password}", arguments = listOf(
            navArgument(name = "email") {
                type = NavType.StringType
            },
            navArgument(name = "password") {
                type = NavType.StringType
            }
        )) {
            val email = it.arguments?.getString("email") ?: ""
            MainScreen(email) {

            }
        }
    }
}

@Composable
fun RegistrationScreen(onClick: () -> Unit) {
    Text("Registration", style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.clickable { onClick() })
}

@Composable
fun LoginScreen(onClick: (abc: String, xyz: String) -> Unit) {
    Text("Login", style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.clickable { onClick("tester", "tester1") })
}

@Composable
fun MainScreen(email: String, onClick: () -> Unit) {
    Text(email, style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.clickable { onClick() })
}