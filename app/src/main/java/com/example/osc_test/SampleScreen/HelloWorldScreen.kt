package com.example.osc_test.SampleScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HelloWorldScreen(
    modifier: Modifier = Modifier,
    sendMessage: () -> Unit = {},
    moveForward: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        Button(onClick = { sendMessage() }) {
            Text(text = "Hello, World")
        }

        Button(onClick = { moveForward()}) {
            Text(text = "Move Forward")
        }
    }
}