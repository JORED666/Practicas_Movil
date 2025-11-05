package com.edmonbl.cuartitocapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.edmonbl.cuartitocapp.ui.theme.CuartitoCAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CuartitoCAppTheme {
                AppNavigation()
            }
        }
    }
}
