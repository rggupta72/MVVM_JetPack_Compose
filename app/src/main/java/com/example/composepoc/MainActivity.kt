package com.example.composepoc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.example.composepoc.domain.DataManager
import com.example.composepoc.domain.Page
import com.example.composepoc.screens.listingScreen
import com.example.composepoc.ui.theme.ComposePOCTheme
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
                    if (DataManager.isDataLoad.value) {
                        if (DataManager.currentPage.value == Page.DASHBOARD) {
                            listingScreen(
                                onClick = {
                                    DataManager.switchPages()
                                }
                            )

                        } else {
                            dummyUi(dummyArray = DataManager.data,
                                textClick = { count ->
                                    launchHealthNeeds(count)
                                }
                            )
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
