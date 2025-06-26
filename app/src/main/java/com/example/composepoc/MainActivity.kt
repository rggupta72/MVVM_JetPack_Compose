@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composepoc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composepoc.domain.DataManager
import com.example.composepoc.view.listingScreen
import com.example.composepoc.view.dummyUi
import com.example.composepoc.view.theme.ComposePOCTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        // background thread
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssetFromFile(applicationContext)
        }
        composeView.setContent {
            ComposePOCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    // because of state used this will update on main thread
//                    if (DataManager.isDataLoad.value) {
//                        if (DataManager.currentPage.value == Page.DASHBOARD) {
//                            listingScreen(
//                                onClick = {
//                                    DataManager.switchPages()
//                                }
//                            )
//
//                        } else {
//                            dummyUi(dummyArray = DataManager.data,
//                                textClick = { count ->
//                                    launchHealthNeeds(count)
//                                }
//                            )
//                        }
//                    }
                    Scaffold(topBar = {
                        TopAppBar(
                            title = {
                                Text("Testing App", color = Color.White)
                            },
                            colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                        )
                    }) {
                        Box(modifier = Modifier.padding(it)) {
                            navGraph()
                        }
                    }


                }
            }
        }
    }

    private fun launchHealthNeeds(count: Int) {
        val intent = Intent(this, HealthNeedsActivity::class.java)
        intent.putExtra("Key", count.toString())
        startActivity(intent)
    }


}

@Composable
private fun navGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "listingScreen") {
        composable(route = "listingScreen") {
            listingScreen() {
                navController.navigate("dummyUi/${"tester"}")
            }
        }
        composable(route = "dummyUi/{argument}", arguments = listOf(
            navArgument(name = "argument") {
                type = NavType.StringType
            }
        )) {
            dummyUi(DataManager.data) {}
        }
    }
}
