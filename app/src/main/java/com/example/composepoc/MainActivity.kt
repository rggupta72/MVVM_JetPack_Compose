@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composepoc

import android.content.Intent
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.composepoc.domain.DataManager
import com.example.composepoc.navgraph.Navgraph
import com.example.composepoc.presentation.viewmodel.ProductEvent
import com.example.composepoc.presentation.viewmodel.ProductListVewModel
import com.example.composepoc.view.MyBottomSheetScreen
import com.example.composepoc.view.dummyUi
import com.example.composepoc.view.listingScreen
import com.example.composepoc.view.practise1
import com.example.composepoc.view.theme.ComposePOCTheme
import com.example.composepoc.worker.DemoWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val productDetailVewModel: ProductListVewModel by viewModels()
    private val workManager = WorkManager.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        // Only traditional method work

        // background thread
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // This block will be executed when the lifecycle is AT LEAST STARTED.
                // It will be cancelled when the lifecycle is STOPPED.
                // It will restart if the lifecycle moves from STOPPED to STARTED again.
                productDetailVewModel.uiState.collectLatest {
                    Log.d("Flow data", it.data?.size.toString() ?: "")
                }
            }
        }
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
                            colors = TopAppBarDefaults.topAppBarColors(Color.Red),
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show()
                                    },
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_format_list_bulleted_24),
                                        contentDescription = ""
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Setting",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_settings_24),
                                        contentDescription = "Setting"
                                    )
                                }
                            }
                        )
                    },
                        bottomBar = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                BottomAppBar(
                                    contentColor = Color.White,
                                    containerColor = Color.Blue
                                ) {
                                    IconButton(onClick = {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Home",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }) {
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_home_24),
                                            contentDescription = ""
                                        )
                                    }
                                    IconButton(onClick = {
                                        Toast.makeText(
                                            this@MainActivity,
                                            "Location",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }) {
                                        Icon(
                                            painter = painterResource(R.drawable.baseline_fmd_good_24),
                                            contentDescription = ""
                                        )
                                    }

                                }

                            }

                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            Navgraph(rememberNavController())
                            // MyBottomSheetScreen()
                        }
                    }


                }
            }
        }
        doWork()
    }

    private fun doWork() {
        // Option 1: Build Constraints separately (often clearer)


        val networkConstraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val batteryConstraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .build()


        val request = OneTimeWorkRequest.Builder(DemoWorker::class.java)
            .setConstraints(networkConstraint)
            .setConstraints(batteryConstraint)
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.EXPONENTIAL, // or .LINEAR
                duration = Duration.ofSeconds(10), // Initial backoff delay (WorkManager.MIN_BACKOFF_MILLIS is 10 seconds)

                // Note: The actual backoff delay might be longer due to system optimizations
                // and will increase with each attempt for EXPONENTIAL policy.
                // The minimum backoff delay is 10 seconds.
            )
            .build()

        // 2. Create the PeriodicWorkRequest
        val repeatingRequest = PeriodicWorkRequest.Builder(
            DemoWorker::class.java,
            repeatInterval = 6, // The interval duration
            repeatIntervalTimeUnit = TimeUnit.HOURS // The unit for the interval
        ).setConstraints(networkConstraint)
            .setConstraints(batteryConstraint)
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.EXPONENTIAL, // or .LINEAR
                duration = Duration.ofSeconds(10), // Initial backoff delay (WorkManager.MIN_BACKOFF_MILLIS is 10 seconds)

                // Note: The actual backoff delay might be longer due to system optimizations
                // and will increase with each attempt for EXPONENTIAL policy.
                // The minimum backoff delay is 10 seconds.
            )
            .build()


        workManager.enqueueUniquePeriodicWork(
            "myUniquePeriodicWorkName",
            androidx.work.ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )

        //  workManager.enqueue(request)

    }


    private fun launchHealthNeeds(count: Int) {
        val intent = Intent(this, HealthNeedsActivity::class.java)
        intent.putExtra("Key", count.toString())
        startActivity(intent)
    }


}

@Preview(showBackground = true)
@Composable
private fun navGraph() {
    val name = "Raju"
    val password = null
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "practise1") {

        composable(route = "practise1") {
            practise1 {
                println(it)
                navController.navigate("listingScreen/$name?password=$password")
            }
        }

        composable(
            route = "listingScreen/{name}?password={password}", arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("password") {
                    type = NavType.StringType
                    defaultValue = "123456"
                    nullable = true
                }
            )
        ) {

            listingScreen(
                it.arguments?.getString("name") ?: "",
                it.arguments?.getString("password") ?: ""
            ) {
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
