package com.ivan.conunterviewmodel_c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivan.conunterviewmodel_c.ui.theme.ConunterViewModel_CTheme
import viewModels.CounterViewModels

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val counterViewModels: CounterViewModels by viewModels()
        enableEdgeToEdge()
        setContent {
            ConunterViewModel_CTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Content(paddingValues = innerPadding, counterViewModels = counterViewModels)
                }
            }
        }
    }
}

@Composable
fun Content(paddingValues: PaddingValues, counterViewModels: CounterViewModels) {
    //var counter = 0
    Column (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "Counter: ${counterViewModels.counter.value}")
        Spacer(modifier = Modifier.height(height = 10.dp))
        Button(
            onClick = {
                counterViewModels.add()
            }
        ) {
            Text(text = "+")
        }
    }
}