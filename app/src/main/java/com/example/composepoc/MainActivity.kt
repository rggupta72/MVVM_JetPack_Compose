package com.example.composepoc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.example.composepoc.ui.theme.ComposePOCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            ComposePOCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    dummyUi(
                        textClick = {
                            launchHealthNeeds()
                        }
                    )
                }
            }
        }
    }

    private fun launchHealthNeeds() {
        val intent = Intent(this, HealthNeedsActivity::class.java)
        intent.putExtra("Key", "Value")
        startActivity(intent)
    }


}
