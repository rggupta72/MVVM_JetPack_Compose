@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.composepoc

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.composepoc.data.di.ApplicationNavGraphProvider
import com.example.composepoc.domain.DataManager
import com.example.composepoc.navgraph.NavigationBase
import com.example.composepoc.presentation.viewmodel.ProductListVewModel
import com.example.composepoc.view.theme.ComposePOCTheme
import com.example.composepoc.worker.DemoWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val productDetailVewModel: ProductListVewModel by viewModels()
    private val workManager = WorkManager.getInstance(this)
    private lateinit var parentNavController: NavHostController

    @Inject
    lateinit var applicationNavGraphProvider: ApplicationNavGraphProvider
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
                    parentNavController = rememberNavController()
                    NavHost(
                        navController = parentNavController,
                        graph = applicationNavGraphProvider.getApplicationNavGraph(
                            navController = parentNavController,
                            startDestination = NavigationBase.PRODUCT_LIST.destination
                        ),
                        modifier = Modifier.fillMaxSize()
                    )
                    super.HandleDeepLink(parentNavController)

//                    Scaffold(topBar = {
//                        TopAppBar(
//                            title = {
//                                Text("Testing App", color = Color.White)
//                            },
//                            colors = TopAppBarDefaults.topAppBarColors(Color.Red),
//                            navigationIcon = {
//                                IconButton(
//                                    onClick = {
//                                        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show()
//                                    },
//                                ) {
//                                    Icon(
//                                        painter = painterResource(R.drawable.baseline_format_list_bulleted_24),
//                                        contentDescription = ""
//                                    )
//                                }
//                            },
//                            actions = {
//                                IconButton(onClick = {
//                                    Toast.makeText(
//                                        this@MainActivity,
//                                        "Setting",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }) {
//                                    Icon(
//                                        painter = painterResource(R.drawable.baseline_settings_24),
//                                        contentDescription = "Setting"
//                                    )
//                                }
//                            }
//                        )
//                    },
//                        bottomBar = {
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
//                                BottomAppBar(
//                                    contentColor = Color.White,
//                                    containerColor = Color.Blue
//                                ) {
//                                    IconButton(onClick = {
//                                        Toast.makeText(
//                                            this@MainActivity,
//                                            "Home",
//                                            Toast.LENGTH_SHORT
//                                        )
//                                            .show()
//                                    }) {
//                                        Icon(
//                                            painter = painterResource(R.drawable.baseline_home_24),
//                                            contentDescription = ""
//                                        )
//                                    }
//                                    IconButton(onClick = {
//                                        Toast.makeText(
//                                            this@MainActivity,
//                                            "Location",
//                                            Toast.LENGTH_SHORT
//                                        )
//                                            .show()
//                                    }) {
//                                        Icon(
//                                            painter = painterResource(R.drawable.baseline_fmd_good_24),
//                                            contentDescription = ""
//                                        )
//                                    }
//
//                                }
//
//                            }
//
//                        }
//                    ) {
//                        Box(modifier = Modifier.padding(it)) {
//                            Navgraph(rememberNavController())
//                            // MyBottomSheetScreen()
//                        }
//                    }


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
}


